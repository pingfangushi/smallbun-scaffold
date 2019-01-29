package org.smallbun.framework.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * 登录成功处理程序
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/29 19:05
 */
public interface LoginSuccessHandler {
	/**
	 * 修改登录信息
	 * @param userDetails {@link UserDetails}
	 */
	void updateLoginInfo(UserDetails userDetails);
}
