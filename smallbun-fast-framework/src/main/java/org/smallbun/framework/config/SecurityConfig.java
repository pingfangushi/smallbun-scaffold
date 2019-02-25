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

package org.smallbun.framework.config;

import org.smallbun.framework.security.LoginAuthenticationProvider;
import org.smallbun.framework.security.SecurityCaptchaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.smallbun.framework.constant.AuthoritiesConstant.IS_PHONE_PATH;
import static org.smallbun.framework.security.SecurityConstant.LOGIN;
import static org.smallbun.framework.security.SecurityConstant.LOGOUT;

/**
 * Spring Security 配置
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/29
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	public SecurityConfig(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,
			LogoutSuccessHandler logoutSuccessHandler, AuthenticationEntryPoint authenticationEntryPoint,
			@Qualifier(value = "UserDetailsService") UserDetailsService userDetailsService) {
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * 不拦截路径
	 *
	 * @return
	 */
	private String[] antPatterns() {
		return new String[]{"/static/**", "/swagger-resources/**", "/webjars/**", "/favicon.ico", "/captcha", "/key",
				"/v2/api-docs/**", "/swagger-resources/**", IS_PHONE_PATH};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//配置验证码过滤器
		http.addFilterBefore(new SecurityCaptchaFilter(LOGIN), UsernamePasswordAuthenticationFilter.class);
		// 解决不允许显示在iFrame的问题
		http.headers().frameOptions().disable();
		http.headers().cacheControl();

		//路径配置
		http.authorizeRequests().antMatchers(antPatterns()).permitAll().anyRequest().authenticated();
		//登录配置
		http.formLogin().loginPage(LOGIN).successHandler(successHandler).failureHandler(failureHandler).permitAll()
				.and().logout().logoutUrl(LOGOUT).and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint);
		//退出配置
		http.logout().logoutSuccessHandler(logoutSuccessHandler).permitAll();

		//session失效跳转的链接
		http.sessionManagement()
				//session失效跳转URL
				.invalidSessionUrl(LOGIN)
				//Spring Security的默认启用防止固化session攻击
				.sessionFixation().migrateSession()
				//用户并发 -1为不限制
				.maximumSessions(-1).sessionRegistry(sessionRegistry()).expiredUrl(LOGIN);
	}


	/**
	 * 配置
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		//使用自定义认证,注入UserDetailsService
		auth.authenticationProvider(new LoginAuthenticationProvider(userDetailsService));

	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 注入登录成功处理器
	 */
	private final AuthenticationSuccessHandler successHandler;
	/**
	 * 注入登录失败处理器
	 */
	private final AuthenticationFailureHandler failureHandler;
	/**
	 * 注入退出成功处理器
	 */
	private final LogoutSuccessHandler logoutSuccessHandler;
	/**
	 * 身份验证入口点
	 */
	private final AuthenticationEntryPoint authenticationEntryPoint;
	/**
	 * 注入自定义用户逻辑实现
	 */
	private final UserDetailsService userDetailsService;


}
