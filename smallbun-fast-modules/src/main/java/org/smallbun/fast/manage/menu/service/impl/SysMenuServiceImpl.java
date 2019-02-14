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

package org.smallbun.fast.manage.menu.service.impl;


import org.smallbun.fast.common.base.BaseTreeDataServiceImpl;
import org.smallbun.fast.manage.menu.dao.SysMenuMapper;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.menu.vo.SysMenuVO;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * 系统菜单业务逻辑实现类
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on 2018/5/2 13:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends BaseTreeDataServiceImpl<SysMenuMapper, SysMenuEntity>
		implements SysMenuService {


	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysMenuVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysMenuVO() :
					mapping(getById(request.getParameter(ID)), new SysMenuVO());
		}
		return new SysMenuVO();
	}

	/**
	 * 获取当前用户具有的用户菜单
	 *
	 * @return List<SysMenuEntity>
	 */
	@Override
	public List<SysMenuVO> userMenus() {
		//获取当前用户
		LoginUserDetails user = UserUtil.getLoginUser();
		//获取用户所属的菜单
		List<SysMenuEntity> list = user.getMenus();
		//循环得到目录和菜单
		final List<SysMenuEntity> permissions = new ArrayList<>();
		for (SysMenuEntity l : list) {
			if (l.getMenuType().equals(0) | l.getMenuType().equals(1)) {
				permissions.add(l);
			}
		}
		//设置子菜单
		setChildren(permissions);
		//过滤菜单，拿到所有的父菜单
		List<SysMenuEntity> result = new ArrayList<>();
		for (SysMenuEntity p : permissions) {
			//判断是否是目录
			if (!p.getParentId().equals(0L)) {continue;}
			result.add(p);
		}
		result = result.stream().distinct().collect(Collectors.toList());
		return mappingList(result, new ArrayList<>(), SysMenuVO.class);
	}

	/**
	 * 根据用户ID进行查询所具有的菜单功能权限
	 *
	 * @param userId {@link Long} 用户ID
	 * @return {@link SysMenuEntity}
	 */
	@Override
	public List<SysMenuEntity> findByUserId(Serializable userId) {
		return baseMapper.findByUserId(userId);
	}

	/**
	 * 根据角色ID进行查询所具有的菜单功能权限
	 *
	 * @param roleId {@link Long} 角色ID
	 * @return {@link SysMenuEntity}
	 */
	@Override
	public List<SysMenuEntity> findByRoleId(Serializable roleId) {
		return baseMapper.findByRoleId(roleId);
	}


	/**
	 * 设置子菜单
	 *
	 * @param permissions permissions
	 */
	private void setChildren(List<SysMenuEntity> permissions) {
		permissions.parallelStream().forEach((SysMenuEntity per) -> {
			List<SysMenuEntity> child = new ArrayList<>();
			for (SysMenuEntity p : permissions) {
				if (p.getParentId().equals(per.getId())) {
					child.add(p);
				}
			}
			per.setChildren(child);
		});
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
	public boolean saveOrUpdate(SysMenuEntity entity) {
		return super.saveOrUpdate(entity);
	}
}
