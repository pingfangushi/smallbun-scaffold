package org.smallbun.fast.manage.monitor.controller;

import org.smallbun.fast.manage.monitor.service.MonitorService;
import org.smallbun.framework.base.BaseController;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统监控控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/13 13:51
 */
@RestController
@RequestMapping(value = "/monitor")
public class MonitorController extends BaseController {
	@Autowired
	public MonitorController(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	/**
	 * 在线用户
	 * @return {@link AjaxResult}
	 */
	@RequestMapping(value = "/online/user/view")
	public ModelAndView onlineView() {
		return new ModelAndView("modules/manage/monitor/online_user.html");
	}

	/**
	 * 在线用户
	 * @return {@link AjaxResult}
	 */
	@RequestMapping(value = "/online/user/list")
	public AjaxResult online() {
		return AjaxResult.builder().result(monitorService.getUsersFromSessionRegistry()).build();
	}

	/**
	 * 下线所有用户，除当前用户除外
	 * @return {@link AjaxResult}
	 */
	@PostMapping(value = "/online/user/expireUserSessions")
	public AjaxResult expireUserSessions() {
		monitorService.expireUserSessions();
		return AjaxResult.builder().build();
	}

	/**
	 * 下线指定用户，根据sessionId
	 * @param sessionId sessionId
	 * @return {@link AjaxResult}
	 */
	@PostMapping(value = "/online/user/expireUserSession")
	public AjaxResult expireUserSession(String sessionId) {
		monitorService.expireUserSession(sessionId);
		return AjaxResult.builder().build();
	}

	/**
	 * MonitorService
	 */
	private final MonitorService monitorService;
}
