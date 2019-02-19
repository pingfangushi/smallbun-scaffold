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

package org.smallbun.fast.manage.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;

/**
 * 用户实体类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUserEntity extends DataEntity<Long> {
	/**
	 * 姓名
	 */
	private String fullName;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	@JSONField(serialize = false)
	private String password;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像地址
	 */
	private String headPortraitUrl;
	/**
	 * 手机号
	 */
	@TableField(strategy = IGNORED)
	private String phone;
	/**
	 * 电话
	 */
	@TableField(strategy = IGNORED)
	private String telephone;
	/**
	 * 邮箱
	 */
	@TableField(strategy = IGNORED)
	private String email;
	/**
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 二维码
	 */
	private String qrCode;
	/**
	 * 最后登陆时间
	 */
	private Timestamp lastLoginTime;
	/**
	 * 上次登录ip
	 */
	private String lastLoginIp;
	/**
	 * 上次登录地址
	 */
	private String lastLoginAddress;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 工号
	 */
	private String jobNumber;
	/**
	 * 身份证号
	 */
	@TableField(strategy = IGNORED)
	private String idCard;
	/**
	 * 用户角色集合
	 */
	@TableField(exist = false)
	private List<SysRoleEntity> roleList = Lists.newArrayList();
	/**
	 * 所属部门
	 */
	private String orgId;
	/**
	 * 所属部门
	 */
	@TableField(exist = false)
	private SysOrgEntity org;
	/**
	 * 归属公司
	 */
	@TableField(exist = false)
	private SysOrgEntity company;
}
