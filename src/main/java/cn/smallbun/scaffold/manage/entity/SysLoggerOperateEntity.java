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
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_logger_operate")
public class SysLoggerOperateEntity extends BaseAuditEntity<String> {

	/**
	 * 模块名称
	 */
	@TableField(value = "module_", condition = SqlCondition.LIKE)
	private String module;
	/**
	 * 请求参数
	 */
	@TableField("params_")
	private String params;

	/**
	 * 功能名称
	 */
	@TableField(value = "feature_", condition = SqlCondition.LIKE)
	private String feature;

	/**
	 * 操作类型
	 */
	@TableField("action_")
	private String action;

	/**
	 * 平台类型
	 */
	@TableField("platform_")
	private String platform;
	/**
	 * 请求方法
	 */
	@TableField("method_")
	private String method;

	/**
	 * 操作用户,用户名(关联用户表)
	 */
	@TableField("user_")
	private String user;

	/**
	 * 操作时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@TableField("time_")
	private LocalDateTime time;

	/**
	 * URI
	 */
	@TableField("uri_")
	private String uri;

	/**
	 * 地点
	 */
	@TableField("location_")
	private String location;

	/**
	 * 浏览器
	 */
	@TableField("browser_")
	private String browser;

	/**
	 * 操作系统
	 */
	@TableField("os_")
	private String os;

	/**
	 * ip
	 */
	@TableField("ip_")
	private String ip;

	/**
	 * 结果
	 */
	@TableField("result_")
	private String result;
	/**
	 * 状态
	 */
	@TableField("status_")
	private String status;
}
