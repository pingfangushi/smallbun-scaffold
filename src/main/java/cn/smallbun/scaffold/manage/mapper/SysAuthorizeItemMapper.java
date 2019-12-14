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
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统操作权限表 Mapper 接口
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
@Mapper
public interface SysAuthorizeItemMapper extends BaseMapper<SysAuthorizeItemEntity> {

	/**
	 * 根据权限ID查询具有的权限
	 * @param auth 主权限ID
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getByAuthId(@Param(value = "authId") String auth);

	/**
	 * 根据角色查询权限项
	 * @param ids ids
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItemsByRole(@Param("ids") List<String> ids);

	/**
	 * 查询所有权限
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItems();
}
