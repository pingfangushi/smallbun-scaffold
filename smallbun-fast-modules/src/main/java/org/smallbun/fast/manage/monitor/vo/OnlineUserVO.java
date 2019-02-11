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

package org.smallbun.fast.manage.monitor.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 用户详细信息描述VO类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/8 22:45
 */
@Data
@Builder
public class OnlineUserVO implements Serializable {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 归属部门
	 */
	private String orgName;
	/**
	 * 登录时间
	 */
	private Date logInTime;
	/***
	 * 最后访问时间
	 */
	private Date lastAccessTime;
	/**
	 * sessionId
	 */
	private String sessionId;
	/**
	 * 登录IP
	 */
	private String logInIp;
	/**
	 * 登录地址
	 */
	private String logInAddress;
	/**
	 * 浏览器
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
}
