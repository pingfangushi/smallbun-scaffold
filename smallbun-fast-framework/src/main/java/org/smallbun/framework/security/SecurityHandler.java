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

package org.smallbun.framework.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallbun.framework.constant.SystemConstant;
import org.smallbun.framework.toolkit.IpUtil;
import org.smallbun.framework.toolkit.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

import static org.smallbun.framework.security.SecurityConstant.LOGIN;
import static org.smallbun.framework.toolkit.AddressUtil.getRealAddressByIP;

/**
 * Spring Security处理器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/29
 */
@Configuration
public class SecurityHandler {

	public static final String USER = "USER";

	@Autowired
	public SecurityHandler(LoginSuccessHandler loginSuccessHandler) {
		this.loginSuccessHandler = loginSuccessHandler;
	}

	@Bean
	public ActiveUserStore activeUserStore() {
		return new ActiveUserStore();
	}

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(SecurityHandler.class);


	/**
	 * 登陆成功
	 *
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return (request, response, authentication) -> {
			//获取当前用户信息
			UserDetails loginUser = UserUtil.getLoginUser();
			logger.info("----------------------------------------------------------");
			logger.info("{}:登录成功!", loginUser != null ? loginUser.getUsername() : null);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
			out.flush();
			out.close();
			//添加登录信息
			putLogInInfo(request);
			//修改登录信息
			loginSuccessHandler.updateLoginInfo(loginUser);
			logger.info("----------------------------------------------------------");
		};
	}


	/**
	 * 登陆失败
	 *
	 * @return {@link AuthenticationFailureHandler}
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return (request, response, exception) -> {
			logger.info("----------------------------------------------------------");
			logger.info("{}:{}!", request.getParameter(SystemConstant.USER_NAME), exception.getMessage());
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\":\"error\",\"msg\":\"" + exception.getMessage() + "\"}");
			out.flush();
			out.close();
			logger.info("----------------------------------------------------------");
		};
	}

	/**
	 * 未登录,返回登录页面
	 *
	 * @return {@link AuthenticationEntryPoint}
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authException) -> {
			logger.info("----------------------------------------------------------");
			logger.info("未登录,或登录过期");
			logger.info("----------------------------------------------------------");
			response.sendRedirect(LOGIN);
		};
	}

	/**
	 * 退出处理
	 *
	 * @return {@link LogoutSuccessHandler}
	 */
	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return (request, response, authentication) -> {
			logger.info("----------------------------------------------------------");
			logger.info("用户退出");
			delLogInInfo(request);
			logger.info("----------------------------------------------------------");
			response.sendRedirect(LOGIN);
		};
	}

	/**
	 * 添加登录信息
	 * @param request {@link HttpServletRequest}
	 */
	private void putLogInInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (!StringUtils.isEmpty(session)) {
			LoggedUser loggedUser = LoggedUser.builder()
					//sessionId
					.sessionId(session.getId())
					//登录时间
					.logInTime(new Date())
					//登录地点
					.logInAddress(getRealAddressByIP(IpUtil.getIpAddr(request)))
					//浏览器
					.browser(UserAgentUtils.getUserAgent(request).getBrowser().getName())
					//操作系统
					.os(UserAgentUtils.getUserAgent(request).getOperatingSystem().getName())
					//登录IP
					.logInIp(request.getRemoteAddr()).build();
			session.setAttribute(USER, new LoggedUserBindingListener(loggedUser, activeUserStore()));
		}
	}

	/**
	 * 移除信息
	 * @param request {@link HttpServletRequest}
	 */
	private void delLogInInfo(HttpServletRequest request) {
		request.getSession().removeAttribute(USER);
	}

	/**
	 * 注入LoginSuccessHandler
	 */
	private final LoginSuccessHandler loginSuccessHandler;


}
