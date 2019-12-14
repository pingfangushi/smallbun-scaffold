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

package cn.smallbun.scaffold.manage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色权限
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/10 20:11
 */
@Data
public class RoleAuthVO implements Serializable {
	/**
	 * 权限ID
	 */
	private String id;
	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 路由权限
	 */
	private List<RoleAuthItem> routes;
	/**
	 * 接口权限
	 */
	private List<RoleAuthItem> interfaces;
	/**
	 * 操作权限
	 */
	private List<RoleAuthItem> operates;

	/**
	 * 权限项，前端展示只需要展示名称即可，所以路由地址和接口url包括权限标识都没有给加入
	 *  isHave字段表示用户是否拥有这个权限，因为返回的是所有数据，所以来用这个标识表示
	 *  isEnable代表权限是否正常启用，如果未启用，前端只是展示并不能操作，如果用户已经有了但是这个权限
	 *  被冻结了，也不能进行操作，只能展示
	 * @author SanLi
	 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/11/10
	 */
	@Data
	public static class RoleAuthItem implements Serializable {
		/**
		 * 名称
		 */
		private String name;
		/**
		 * 权限值
		 */
		private String permission;
		/**
		 * id
		 */
		private String id;
		/**
		 * 禁用
		 */
		private boolean disabled;
		/**
		 * 是否选中
		 */
		private boolean checked;

	}
}