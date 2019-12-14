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
import cn.smallbun.scaffold.manage.entity.SysDictItemEntity;
import cn.smallbun.scaffold.manage.enums.DictDefault;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 系统字典数据 服务类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
public interface ISysDictItemService extends BaseService<SysDictItemEntity>, InitInterface {
	/**
	 * 是否存在默认
	 * @param type 类型
	 * @return boolean
	 */
	boolean isExistDefault(Serializable type);

	/**
	 * 根据字典ID更新是否默认值
	 * @param id ID
	 * @param isDefault 默认值
	 * @return 是否成功
	 */
	boolean updateIsDefaultById(String id, DictDefault isDefault);

	/**
	 * 根据type类型删除数据
	 * @param idList idList
	 * @return boolean
	 */
	boolean removeByTypes(Collection<? extends Serializable> idList);
}
