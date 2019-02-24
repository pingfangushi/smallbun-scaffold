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

package org.smallbun.fast.manage.monitor.controller;

import org.smallbun.fast.manage.monitor.service.SysMonitorService;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.constant.OperateLogConstant;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;

import static org.smallbun.framework.constant.ErrorMsgConstant.ID_NOT_BLANK_MSG;
import static org.smallbun.framework.constant.OperateLogConstant.SELECT_LIST_MODEL;

/**
 * 系统监控控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/13 13:51
 */
@Validated
@RestController
@RequestMapping(value = "/monitor")
public class SysMonitorController extends BaseController {
	/**
	 * 模块名称
	 */
	private static final String MODEL = "在线用户";
	/**
	 * HTML页面路径前缀
	 */
	private static final String HTML_PREFIX = "modules/manage/monitor/";

	@Autowired
	public SysMonitorController(SysMonitorService monitorService) {
		this.monitorService = monitorService;
	}

	/**
	 * 在线用户数量
	 * @return {@link AjaxResult}
	 */
	@RequestMapping(value = "/online/user/count")
	public AjaxResult onlineUserCount() {
		return AjaxResult.builder().result(monitorService.onlineUserCount()).build();
	}

	/**
	 * 在线用户
	 * @return {@link AjaxResult}
	 */
	@RequestMapping(value = "/online/user/view")
	public ModelAndView onlineView() {
		return new ModelAndView(HTML_PREFIX + "online_user.html");
	}

	/**
	 * 在线用户
	 * @return {@link AjaxResult}
	 */
	@RequestMapping(value = "/online/user/list")
	@LogAnnotation(model = MODEL + SELECT_LIST_MODEL, action = OperateLogConstant.DEL)
	public AjaxResult online() {
		return AjaxResult.builder().result(monitorService.getUsersFromSessionRegistry()).build();
	}

	/**
	 * 下线所有用户，除当前用户除外
	 * @return {@link AjaxResult}
	 */
	@DemoEnvironment
	@PostMapping(value = "/online/user/expireUserSessions")
	@LogAnnotation(model = MODEL + "下线所有用户，当前用户除外", action = OperateLogConstant.DEL)
	public AjaxResult expireUserSessions() {
		monitorService.expireUserSessions();
		return AjaxResult.builder().build();
	}

	/**
	 * 下线指定用户，根据sessionId
	 * @param sessionId sessionId
	 * @return {@link AjaxResult}
	 */
	@DemoEnvironment
	@PostMapping(value = "/online/user/expireUserSession")
	@LogAnnotation(model = MODEL + "下线指定用户", action = OperateLogConstant.DEL)
	public AjaxResult expireUserSession(
			@NotNull(message = ID_NOT_BLANK_MSG) @RequestParam(value = "sessionId") String sessionId) {
		monitorService.expireUserSession(sessionId);
		return AjaxResult.builder().build();
	}

	/**
	 * MonitorService
	 */
	private final SysMonitorService monitorService;
}
