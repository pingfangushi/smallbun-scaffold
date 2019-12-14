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

import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * UpdateRoleAuthBO
 * @author SanLis
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/11 15:22
 */
@Data
public class UpdateAuthorizeBO implements Serializable {
	/**
	 * 角色ID
	 */
	@NotBlank(message = "请选择角色ID")
	private String id;
	/**
	 * 权限ID
	 */
	@NotBlank(message = "请选择权限ID")
	private String auth;
	/**
	 * 权限类型
	 */
	@NotNull(message = "请选择权限类型")
	private AuthorizeType type;
	/**
	 * 是否设置
	 */
	@NotNull(message = "请选择是否设置")
	private Boolean checked;
}
