/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.manage.core.controller;

import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.monitor.service.SysMonitorService;
import org.smallbun.fast.manage.user.util.UserUtil;
import org.smallbun.framework.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ApplicationController(SysMenuService sysMenuService, SysMonitorService monitorService) {
		this.sysMenuService = sysMenuService;
		this.monitorService = monitorService;
	}

	/**
	 * 登录页面跳转地址
	 *
	 * @return 登录页面
	 */
	@RequestMapping(LOGIN)
	public ModelAndView login(HttpServletRequest request) {
		//退出当前用户
		monitorService.expireUserSession(request.getSession().getId());
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
		return new ModelAndView("modules/common/icons");
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


}
