package org.smallbun.fast.manage.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:35
 */

@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
	/**
	 * 获取全部数据
	 * @param role {@link SysRoleEntity}
	 * @return 角色集合
	 */
	List<SysRoleEntity> findAll(SysRoleEntity role);

	/**
	 * 根据用户ID获取所具有的角色
	 *
	 * @param userId 用户ID
	 * @return 角色集合
	 */
	List<SysRoleEntity> findByUserId(@Param("userId") String userId);

	/**
	 * 按角色ID删除角色组织
	 * @param id
	 */
	void deleteRoleOrgByRoleId(@Param("id") Serializable id);

	/**
	 * 批量添加角色-部门
	 * @param entity
	 */
	void batchRoleOrgInsert(@Param("entity") SysRoleEntity entity);

	/**
	 * 根据角色id删除角色菜单
	 * @param id
	 */
	void deleteRoleMenuByRoleId(@Param("id") Serializable id);

	/**
	 * 批量添加角色-菜单
	 * @param entity
	 */
	void batchRoleMenuInsert(@Param("entity") SysRoleEntity entity);

	/**
	 * 保存角色-用户关联
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	boolean saveRoleUser(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}
