/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.manage.entity.SysAuthorityTypeEntity;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.enums.AuthorizeStatus;
import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import cn.smallbun.scaffold.manage.enums.RoleStatus;
import cn.smallbun.scaffold.manage.mapper.SysRoleMapper;
import cn.smallbun.scaffold.manage.pojo.RoleAuthBO;
import cn.smallbun.scaffold.manage.pojo.RoleAuthVO;
import cn.smallbun.scaffold.manage.pojo.UpdateAuthorizeBO;
import cn.smallbun.scaffold.manage.service.ISysAuthorityTypeService;
import cn.smallbun.scaffold.manage.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

	public SysRoleServiceImpl(ISysAuthorityTypeService iSysAuthorityService) {
		this.authorityService = iSysAuthorityService;
	}

	/**
	 * 保存角色和用户
	 * @param userId userId
	 * @param roleIds roleIds
	 * @return boolean
	 */
	@Override
	public boolean saveRoleUser(String userId, List<String> roleIds) {
		return baseMapper.saveRoleUser(userId, roleIds);
	}

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id id
	 * @return boolean
	 */
	@Override
	public boolean removeByUserId(String id) {
		return baseMapper.removeByUserId(id);
	}

	/**
	 * 根据用户查询角色
	 * @param id id
	 * @return {@link List<SysRoleEntity>}
	 */
	@Override
	public List<SysRoleEntity> getByUserId(String id) {
		return baseMapper.findByUserId(id);
	}

	/**
	 * 根据角色获取拥有的权限
	 * @param id id
	 * @return List<RoleAuthVO>
	 */
	@Override
	public List<RoleAuthVO> getRoleAuthById(String id) {
		List<RoleAuthVO> vos = new ArrayList<>();
		//1.首先查询到所有的权限
		List<SysAuthorityTypeEntity> list = authorityService.getAuthoritys();
		// 获取角色关联关系
		List<RoleAuthBO> bos = baseMapper.getRoleAuthById(id);
		//2.根据角色查询拥有的，然后对数据进行处理返回
		for (SysAuthorityTypeEntity entity : list) {
			RoleAuthVO vo = new RoleAuthVO();
			//id
			vo.setId(entity.getId());
			//名称
			vo.setName(entity.getName());
			//路由权限
			vo.setRoutes(getItems(entity.getItems(), bos, AuthorizeType.ROUTE));
			//操作权限
			vo.setOperates(getItems(entity.getItems(), bos, AuthorizeType.OPERATE));
			//接口权限
			vo.setInterfaces(getItems(entity.getItems(), bos, AuthorizeType.INTERFACE));
			vos.add(vo);
		}
		return vos;
	}


	/**
	 * getItems
	 * @param routes {@link List<  SysAuthorizeItemEntity  >}
	 * @param bos {@link List<RoleAuthBO>}
	 * @return {@link List<RoleAuthVO.RoleAuthItem>}
	 */
	private List<RoleAuthVO.RoleAuthItem> getItems(List<SysAuthorizeItemEntity> routes, List<RoleAuthBO> bos,
			AuthorizeType type) {
		//获取此类型的权限原始集合
		routes = routes.stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList());
		//获取拥有此类型的权限集合
		bos = bos.stream().filter(i -> type.getCode().equals(i.getType())).collect(Collectors.toList());
		List<RoleAuthVO.RoleAuthItem> items = Lists.newArrayList();
		//封装所有权限
		for (SysAuthorizeItemEntity route : routes) {
			items.add(getRoleAuthItem(route.getId(), route.getName(), route.getPermission(), route.getStatus()));
		}
		//处理拥有权限
		getHaveAuth(bos, items);
		return items;
	}

	/**
	 * 获取权限项
	 * @param id id 权限ID
	 * @param name 名称
	 * @param status 状态
	 */
	private RoleAuthVO.RoleAuthItem getRoleAuthItem(String id, String name, String permission, AuthorizeStatus status) {
		RoleAuthVO.RoleAuthItem item = new RoleAuthVO.RoleAuthItem();
		item.setId(id);
		item.setName(name);
		item.setPermission(permission);
		item.setDisabled(AuthorizeStatus.DISABLE.equals(status));
		return item;
	}

	/**
	 * 处理拥有权限
	 * @param bos bos
	 * @param items items
	 */
	private void getHaveAuth(List<RoleAuthBO> bos, List<RoleAuthVO.RoleAuthItem> items) {
		for (RoleAuthBO bo : bos) {
			for (RoleAuthVO.RoleAuthItem item : items) {
				if (item.getId().equals(bo.getAuth())) {
					item.setChecked(true);
				}
			}
		}
	}

	/**
	 * 更新权限信息
	 * @param auth auth
	 * @return Boolean
	 */
	@Override
	public boolean updateAuthorize(UpdateAuthorizeBO auth) {
		String[] ids = auth.getAuth().split(",");
		boolean flag = false;
		for (String id : ids) {
			//选中，直接添加
			if (auth.getChecked()) {
				flag = baseMapper.saveRoleAuthorize(auth.getId(), auth.getType().getCode(), id) > 0;
				continue;
			}
			// 取消,直接删除
			flag = baseMapper.removeRoleAuthorize(auth.getId(), auth.getType().getCode(), id) > 0;
		}
		return flag;
	}

	/**
	 * 根据角色ID更新状态
	 * @param id id
	 * @param status status
	 * @return boolean
	 */
	@Override
	public boolean updateStatusById(String id, RoleStatus status) {
		return update(new LambdaUpdateWrapper<SysRoleEntity>()
				// ID
				.eq(SysRoleEntity::getId, id)
				// 设置装填
				.set(SysRoleEntity::getStatus, status));
	}

	/**
	 * 注入权限Service
	 */
	private final ISysAuthorityTypeService authorityService;
}
