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

package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.framework.initialize.InitInterface;
import cn.smallbun.scaffold.framework.mybatis.service.BaseService;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 系统操作权限表 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-09
 */
public interface ISysAuthorizeItemService extends BaseService<SysAuthorizeItemEntity>, InitInterface {
	/**
	 * 通过角色获取权限项
	 * @param ids ids
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItemsByRole(List<String> ids);

	/**
	 * 查询所有权限项目
	 * @return {@link List<SysAuthorizeItemEntity>}
	 */
	List<SysAuthorizeItemEntity> getAuthorizeItems();

	/**
	 * removeByTypes
	 * @param idList {@link List}
	 * @return boolean
	 */
	boolean removeByTypes(Collection<? extends Serializable> idList);
}
