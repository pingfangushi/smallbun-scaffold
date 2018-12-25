package org.smallbun.fast.manage.dashboard.controller;

import org.smallbun.fast.manage.dashboard.service.DashboardService;
import org.smallbun.framework.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 仪表盘控制器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/7 22:48
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {
	@Autowired
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	/**
	 * 仪表盘页面
	 * @return 页面路径
	 */
	@RequestMapping
	public ModelAndView dashboard() {
		return new ModelAndView("modules/manage/dashboard/dashboard.html");
	}

	private final DashboardService dashboardService;
}
