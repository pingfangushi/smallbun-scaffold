/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.role.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.org.service.SysOrgService;
import org.smallbun.fast.manage.org.vo.SysOrgVO;
import org.smallbun.fast.manage.role.dao.SysRoleMapper;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.permission.constant.DataScopeConstant.DATA_SCOPE_CUSTOM;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:42
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

	@Autowired
	public SysRoleServiceImpl(SysMenuService sysMenuService, SysOrgService sysOrgService) {
		this.sysMenuService = sysMenuService;
		this.sysOrgService = sysOrgService;
	}

	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysRoleVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return getById(request.getParameter(ID));
		}
		return new SysRoleVO();
	}

	/**
	 * 保存角色和用户
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveRoleUser(String userId, List<String> roleIds) {
		return baseMapper.saveRoleUser(userId, roleIds);

	}

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id
	 * @return
	 */
	@Override
	public boolean delRoleUserByUserId(Long id) {
		return baseMapper.delRoleUserByUserId(id);
	}

	/**
	 * 根据编号获取id
	 *
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SysRoleVO getById(Serializable id) {
		SysRoleVO role = new SysRoleVO();
		if (!StringUtils.isEmpty(id)) {
			//转为VO
			role = mapping(super.getById(id), new SysRoleVO());
			if (!StringUtils.isEmpty(id)) {
				//放入当前用户拥有的菜单JSON字符串
				role.setMenusJson(JSONObject.toJSONString(sysMenuService.findByRoleId(id)));
			}
			//如果是按照明细设置
			if (DATA_SCOPE_CUSTOM.getNumber().equals(role.getDataScope())) {
				//放入当前用户拥有的部门权限JSON字符串
				role.setOrgsJson(JSONObject
						.toJSONString(mappingList(sysOrgService.findByRoleId(id), new ArrayList<>(), SysOrgVO.class)));
			}
		}
		return role;
	}

	/**
	 * <p>
	 * TableId 注解存在更新记录，否插入一条记录
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveOrUpdate(SysRoleEntity entity) {
		boolean flag;
		//添加角色表信息,
		flag = super.saveOrUpdate(entity)
				//添加角色-用户菜单表信息
				&& saveOrUpdateRoleMenu(entity)
				//添加角色-明细设置部门范围
				&& saveOrUpdateRoleOrg(entity);
		return flag;
	}

	/**
	 * 保存或添加角色对应部门数据权限
	 * @param entity {@link SysRoleEntity}
	 * @return boolean
	 */
	private boolean saveOrUpdateRoleOrg(SysRoleEntity entity) {
		//1.根据角色删除数据
		baseMapper.deleteRoleOrgByRoleId(entity.getId());
		if (!CollectionUtils.isEmpty(entity.getOrgList())) {
			//2.进行批量添加
			baseMapper.batchRoleOrgInsert(entity);
		}
		return true;
	}

	/**
	 * 保存或添加角色对应菜单
	 * @param entity {@link SysRoleEntity}
	 * @return boolean
	 */
	private boolean saveOrUpdateRoleMenu(SysRoleEntity entity) {
		//1.根据角色删除数据
		baseMapper.deleteRoleMenuByRoleId(entity.getId());
		if (!CollectionUtils.isEmpty(entity.getMenuList())) {
			//2.进行批量添加
			baseMapper.batchRoleMenuInsert(entity);
		}
		return true;
	}

	/**
	 * 注入菜单
	 */
	private final SysMenuService sysMenuService;
	/**
	 * 注入部门
	 */
	private final SysOrgService sysOrgService;
}
