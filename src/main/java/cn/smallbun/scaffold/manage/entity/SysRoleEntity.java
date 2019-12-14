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

package cn.smallbun.scaffold.manage.entity;

import cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditEntity;
import cn.smallbun.scaffold.framework.validation.group.AddGroup;
import cn.smallbun.scaffold.framework.validation.group.UpdateGroup;
import cn.smallbun.scaffold.manage.enums.RoleStatus;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "角色参数", description = "系统角色")
public class SysRoleEntity extends BaseAuditEntity<String> {
	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称")
	@NotBlank(message = "请输入角色名称", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(value = "name_", condition = SqlCondition.LIKE)
	private String name;

	/**
	 * 英文名称
	 */
	@ApiModelProperty(value = "角色编码")
	@NotBlank(message = "请输入角色编码", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("code_")
	private String code;

	/**
	 * 数据范围
	 */
	@ApiModelProperty(value = "数据范围")
	@NotNull(message = "请选择数据范围")
	@TableField("data_scope")
	private Integer dataScope;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@NotNull(message = "请选择角色状态", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("status_")
	private RoleStatus status;
}
