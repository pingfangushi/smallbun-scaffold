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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((LoginUserDetails) principal);
		}
		return null;
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

	public void shuaxin() {
		//用于存储修改之后的权限列表
		List<GrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("addUser"));
		authList.add(new SimpleGrantedAuthority("editUser"));
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), authList);
		//重新设置上下文中存储的用户权限
		context.setAuthentication(auth);
	}
}
