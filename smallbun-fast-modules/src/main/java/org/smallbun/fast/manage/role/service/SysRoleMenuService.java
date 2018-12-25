package org.smallbun.fast.manage.role.service;

import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.entity.SysRoleMenuEntity;
import org.smallbun.framework.base.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:34
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {
	/**
	 * 根据角色编号获取记录
	 * @param id id
	 * @return list
	 */
	List<SysRoleMenuEntity> findByRoleId(Serializable id);

	/**
	 * 批量添加修改
	 * @param role role
	 * @param menus menus
	 * @return boolean
	 */
	boolean saveOrUpdateRoleMenu(SysRoleEntity role, List<SysMenuEntity> menus);
}
