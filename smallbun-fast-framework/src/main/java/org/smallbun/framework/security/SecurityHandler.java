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

package org.smallbun.framework.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallbun.framework.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

import static org.smallbun.framework.security.SecurityConstant.LOGIN;

/**
 * Spring Security处理器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/29
 */
@Configuration
public class SecurityHandler {

	private static final String USER = "USER";
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
			assert loginUser != null;
			logger.info("{}:登录成功!", loginUser.getUsername());
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
			out.flush();
			out.close();
			HttpSession session = request.getSession(true);
			if (!StringUtils.isEmpty(session)) {
				LoggedUser loggedUser = LoggedUser.builder().sessionId(session.getId()).logInTime(new Date())
						.logInIp(request.getRemoteAddr()).build();
				session.setAttribute(USER, new LoggedUserBindingListener(loggedUser));
			}

			logger.info("----------------------------------------------------------");
		};
	}

	/**
	 * 登陆失败
	 *
	 * @return
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
	 * @return
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
	 * @return
	 */
	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return (request, response, authentication) -> {
			logger.info("----------------------------------------------------------");
			logger.info("用户退出");
			logger.info("----------------------------------------------------------");
			response.sendRedirect(LOGIN);
		};
	}
}
