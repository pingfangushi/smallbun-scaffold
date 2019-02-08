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

package org.smallbun.fast.manage.user.util;

import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.service.impl.UserDetailsServiceImpl;
import org.smallbun.framework.toolkit.SpringContextUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

/**
 * User 工具类
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/5/7
 */
public class UserUtil {
	/**
	 * 获取当前登录用户
	 *
	 * @return {@link LoginUserDetails}
	 */
	public static LoginUserDetails getLoginUser() {
		//获取当前用户信息
		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (StringUtils.isEmpty(details)) {
			details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		if (details instanceof UserDetails) {
			return ((LoginUserDetails) details);
		} else {
			details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return ((LoginUserDetails) details);
		}
	}

	/**
	 * 得到当前用户Session会话
	 * @return {@link HttpSession}
	 */
	public static HttpSession getSession() {
		//获取原始会话
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		// true == allow create
		return attr.getRequest().getSession(true);
	}

	/**
	 * 刷新 LoginUserDetails
	 *
	 */
	public static void refresh() {
		UserDetailsService detailsService = getUserDetailsService();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//重新查询载入 LoginUserDetails
		LoginUserDetails userDetails = (LoginUserDetails) detailsService
				.loadUserByUsername(((LoginUserDetails) auth.getPrincipal()).getUsername());
		//设置权限
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
				auth.getCredentials(), userDetails.getAuthorities());

		//设置setDetails
		newAuth.setDetails(userDetails);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

	/**
	 * 刷新所有 LoginUserDetails
	 *
	 */
	public static void refreshAll() {
		SessionRegistry sessionRegistry = (SessionRegistry) SpringContextUtil.getBean(SessionRegistry.class);
		for (LoginUserDetails details : sessionRegistry.getAllPrincipals().stream().map(u -> (LoginUserDetails) u)
				.collect(Collectors.toList())) {
			//排除不是当前用户sessionId的用户
			sessionRegistry.getAllSessions(details, true).forEach(u -> {
				UserDetailsService detailsService = getUserDetailsService();
				//重新查询载入 LoginUserDetails
				LoginUserDetails userDetails = (LoginUserDetails) detailsService
						.loadUserByUsername(((LoginUserDetails) u.getPrincipal()).getUsername());
				//设置权限
				UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(u.getPrincipal(),
						((LoginUserDetails) u.getPrincipal()).getPassword(), userDetails.getAuthorities());

				//设置setDetails
				newAuth.setDetails(userDetails);
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			});
		}
	}

	/**
	 * 获取 UserDetailsService
	 * @return {@link UserDetailsService}
	 */
	private static UserDetailsService getUserDetailsService() {
		return (UserDetailsService) SpringContextUtil.getBean(UserDetailsServiceImpl.class);
	}
}
