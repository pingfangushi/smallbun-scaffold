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
import cn.smallbun.scaffold.manage.enums.UserStatus;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "用户参数", description = "系统用户")
public class SysUserEntity extends BaseAuditEntity<String> {

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	@NotBlank(message = "请输入用户名", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(value = "username_", condition = SqlCondition.LIKE)
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	@TableField("password_hash")
	@JSONField(serialize = false)
	private String passwordHash;

	/**
	 * 所属组织
	 */
	@ApiModelProperty(value = "所属组织")
	@NotNull(message = "请选择组织", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("group_")
	private String groupId;

	/**
	 * 姓名
	 */
	@TableField("name_")
	private String name;
	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private String nickName;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	@TableField(value = "email_", updateStrategy = FieldStrategy.IGNORED)
	private String email;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	@TableField(value = "phone_", updateStrategy = FieldStrategy.IGNORED)
	private String phone;

	/**
	 * 头像URL
	 */
	@TableField("head_portrait_url")
	private String headPortraitUrl;

	/**
	 * 身份证号
	 */
	@TableField(value = "id_card", updateStrategy = FieldStrategy.IGNORED)
	private String idCard;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@NotNull(message = "请选择用户状态", groups = {AddGroup.class, UpdateGroup.class})
	@TableField("status_")
	private UserStatus status;

	/**
	 * roles
	 */
	@ApiModelProperty(value = "拥有角色")
	@TableField(exist = false)
	private List<SysRoleEntity> roles;
	/**
	 * SysGroupEntity
	 */
	@ApiModelProperty(value = "归属组织")
	@TableField(exist = false)
	private SysGroupEntity group;
}
