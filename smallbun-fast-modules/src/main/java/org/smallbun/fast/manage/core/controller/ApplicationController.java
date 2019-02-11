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

package org.smallbun.fast.manage.core.controller;

import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.monitor.service.SysMonitorService;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.smallbun.framework.constant.AuthoritiesConstant.IS_PHONE_403_VIEW;
import static org.smallbun.framework.constant.AuthoritiesConstant.IS_PHONE_PATH;
import static org.smallbun.framework.security.SecurityConstant.LOGIN;

/**
 * 应用程序控制器
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2018-10-09 16:45
 */
@RestController
public class ApplicationController extends BaseController {
	@Autowired
	public ApplicationController(SysMenuService sysMenuService, SysMonitorService monitorService,
			SmallBunProperties smallBunProperties) {
		this.sysMenuService = sysMenuService;
		this.monitorService = monitorService;
		this.smallBunProperties = smallBunProperties;
	}

	/**
	 * 登录页面跳转地址
	 *
	 * @return 登录页面
	 */
	@RequestMapping(LOGIN)
	public ModelAndView login(HttpServletRequest request, Model model) {
		//退出当前用户
		monitorService.expireUserSession(request.getSession().getId());
		//演示模式
		if (smallBunProperties.getDemo().isOpen()) {
			model.addAttribute("open", smallBunProperties.getDemo().isOpen());
			model.addAttribute("username", smallBunProperties.getDemo().getUsername());
			model.addAttribute("password", smallBunProperties.getDemo().getPassword());
		}
		return new ModelAndView("login");
	}

	/**
	 * 主页跳转地址
	 *
	 * @return 主页
	 */
	@RequestMapping({"/index", "/", ""})
	public ModelAndView index(ModelMap model) {
		//返回菜单
		model.addAttribute("menus", sysMenuService.userMenus());
		//用户信息
		model.addAttribute("user", Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser());
		return new ModelAndView("index");
	}

	/**
	 * icon
	 * @return {@link ModelAndView}
	 */
	@RequestMapping("/icon")
	public ModelAndView icon() {
		return new ModelAndView("/common/icons");
	}


	/**
	 * 手机端访问
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(IS_PHONE_PATH)
	public ModelAndView phone() {
		return new ModelAndView(IS_PHONE_403_VIEW);
	}

	/**
	 * 系統菜单业务逻辑接口
	 */
	private final SysMenuService sysMenuService;
	/**
	 * 系统监控
	 */
	private final SysMonitorService monitorService;
	/**
	 * SmallBunProperties
	 */
	private final SmallBunProperties smallBunProperties;


}
