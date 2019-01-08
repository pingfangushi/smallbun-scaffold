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

package org.smallbun.framework.config;

import org.smallbun.framework.security.LoginAuthenticationProvider;
import org.smallbun.framework.security.SecurityCaptchaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@EnableGlobalMethodSecurity(securedEnabled = true)
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
				//用户并发
				.maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl(LOGIN);
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
