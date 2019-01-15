/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.manage.menu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.framework.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	/**
	 * 根据用户ID进行查询所具有的菜单功能权限
	 *
	 * @param userId {@link Long} 用户ID
	 * @return {@link SysMenuEntity}
	 */
	@Select("select distinct p.* from sys_menu p inner join sys_role_menu rp on p.id = rp.menu_id inner join sys_user_role ru on ru.role_id = rp.role_id where ru.user_id = #{userId} and p.is_deleted=0 and p.menu_status=0 order by p.sort")
	List<SysMenuEntity> findByUserId(@Param("userId") Serializable userId);

	/**
	 * 根据角色ID进行查询所具有的菜单功能权限
	 *
	 * @param roleId {@link Long} 角色ID
	 * @return {@link SysMenuEntity}
	 */
	List<SysMenuEntity> findByRoleId(@Param("roleId") Serializable roleId);

}
