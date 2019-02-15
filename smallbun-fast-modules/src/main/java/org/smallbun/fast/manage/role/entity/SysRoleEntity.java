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

package org.smallbun.fast.manage.role.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;
import org.smallbun.fast.manage.menu.entity.SysMenuEntity;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 角色实体类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 20:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
@JsonIgnoreProperties(value = {"handler"})
public class SysRoleEntity extends DataEntity<Long> {

	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空")
	@TableField(condition = SqlCondition.LIKE)
	private String roleName;
	/**
	 * 英文名称
	 */
	@NotBlank(message = "英文名称不能为空")
	private String enName;

	/**
	 * 可查看的数据范围(1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置)
	 */
	@NotBlank(message = "请选择数据范围")
	private String dataScope;
	/**
	 * 角色类型
	 */
	@NotBlank(message = "请选择角色类型")
	private String roleType;

	/**
	 * 是否可用
	 */
	@NotBlank(message = "请选择是否可用")
	private String useable;

	/**
	 * 是否系统数据（默认否）
	 */
	private String sysData;
	/**
	 * 拥有用户列表(缓存)
	 */
	@TableField(exist = false)
	private List<SysUserEntity> userList = Lists.newArrayList();
	/**
	 * 拥有菜单列表(缓存)
	 */
	@TableField(exist = false)
	private List<SysMenuEntity> menuList = Lists.newArrayList();
	/**
	 * 按明细设置数据范围(缓存)
	 */
	@TableField(exist = false)
	private List<SysOrgEntity> orgList = Lists.newArrayList();
}
