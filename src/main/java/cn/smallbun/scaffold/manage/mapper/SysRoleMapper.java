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

package cn.smallbun.scaffold.manage.mapper;

import cn.smallbun.scaffold.framework.mybatis.mapper.BaseMapper;
import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.pojo.RoleAuthBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
	/**
	 * 保存角色-用户关联
	 * @param userId userId
	 * @param roleIds roleIds
	 * @return boolean
	 */
	boolean saveRoleUser(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

	/**
	 * 删除角色-用户关联根据用户ID
	 * @param id id
	 * @return boolean
	 */
	boolean removeByUserId(@Param("id") String id);

	/**
	 * 根据用户ID查询角色信息
	 * @param id id
	 * @return {@link List<SysRoleEntity>}
	 */
	List<SysRoleEntity> findByUserId(String id);

	/**
	 * 添加角色权限关联关系
	 * @param id ID
	 * @param type 类型
	 * @param auth auth
	 * @return Long
	 */
	Long saveRoleAuthorize(@Param("id") String id, @Param("type") String type, @Param("auth") String auth);

	/**
	 * 删除角色权限关联关系
	 * @param id ID
	 * @param type 类型
	 * @param auth auth
	 * @return Long
	 */
	Long removeRoleAuthorize(@Param("id") String id, @Param("type") String type, @Param("auth") String auth);

	/**
	 * 根据角色ID获取权限关联关系
	 * @param id 角色ID
	 * @return {@link List<  RoleAuthBO  >}
	 */
	List<RoleAuthBO> getRoleAuthById(@Param("id") String id);
}
