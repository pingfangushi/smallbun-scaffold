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

package org.smallbun.fast.manage.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.smallbun.fast.common.entity.DataEntity;
import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author SanLi
 * @since 2019-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_operate_log")
public class SysOperateLogEntity extends DataEntity<Long> {

	/**
	 * 模块标题
	 */
	private String title;

	/**
	 * 功能请求
	 */
	private String action;

	/**
	 * 方法名称
	 */
	private String method;

	/**
	 * 来源渠道（0 后台用户 1 手机端用户 2其它）
	 */
	private String channel;

	/**
	 * 操作人员
	 */
	private String operateUser;
	/***
	 * 用户
	 */
	@TableField(exist = false)
	private SysUserEntity user;
	/**
	 * 归属部门
	 */
	private String operateOrg;
	/***
	 * 部门
	 */
	@TableField(exist = false)
	private SysOrgEntity org;

	/**
	 * 请求URL
	 */
	private String operateUrl;

	/**
	 * 主机地址
	 */
	private String operateIp;

	/**
	 * 操作地点
	 */
	private String operateLocation;
	/**
	 * 请求参数
	 */
	private String operateParam;

	/**
	 * 操作状态（0 正常 1 异常）
	 */
	private String operateStatus;

	/**
	 * 错误消息
	 */
	private String errorMsg;

	/**
	 * 操作时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime operateTime;
}
