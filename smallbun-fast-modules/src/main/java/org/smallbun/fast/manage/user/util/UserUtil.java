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

package org.smallbun.fast.manage.user.util;

import org.smallbun.fast.manage.org.entity.SysOrgEntity;
import org.smallbun.fast.manage.user.details.LoginUserDetails;
import org.smallbun.fast.manage.user.service.impl.UserDetailsServiceImpl;
import org.smallbun.framework.toolkit.SpringContextUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

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
		refresh();
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
	 * 刷新当前登录用户的 LoginUserDetails
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
	 * 获取 UserDetailsService
	 * @return {@link UserDetailsService}
	 */
	private static UserDetailsService getUserDetailsService() {
		return (UserDetailsService) SpringContextUtil.getBean(UserDetailsServiceImpl.class);
	}

	/**
	 * 获取  用户ID
	 * @return
	 */
	public static Long getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((LoginUserDetails) principal).getSysUser().getId();
		}
		return null;
	}

	/**
	 * 获取用户归属部门
	 * @return
	 */
	public static SysOrgEntity getUserOrg() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((LoginUserDetails) principal).getSysUser().getOrg();
		}
		return null;
	}
}
