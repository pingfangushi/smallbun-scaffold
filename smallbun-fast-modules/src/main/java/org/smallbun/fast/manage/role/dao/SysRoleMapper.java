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

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id
	 * @return
	 */
	boolean delRoleUserByUserId(@Param("id") Long id);
}
