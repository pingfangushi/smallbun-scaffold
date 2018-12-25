package org.smallbun.fast.manage.role.service;

import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.entity.SysRoleOrgEntity;
import org.smallbun.framework.base.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * 角色-部门（按照明细设置，角色可以看到那个部门的数据）
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/23 21:43
 */
public interface SysRoleOrgService extends BaseService<SysRoleOrgEntity> {
	/**
	 *  添加或更新角色-部门
	 * @param entity entity
	 * @param orgList orgList
	 * @return boolean
	 */
	boolean saveOrUpdateRoleOrg(SysRoleEntity entity, List<SysOrgEntity> orgList);

	/**
	 * 根据角色id获取记录
	 * @param id id
	 * @return {@link List}
	 */
	List<SysRoleOrgEntity> findByRoleId(Serializable id);
}
