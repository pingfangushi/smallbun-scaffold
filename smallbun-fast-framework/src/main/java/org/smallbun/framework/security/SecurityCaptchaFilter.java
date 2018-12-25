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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import static org.smallbun.framework.security.SecurityConstant.LOGIN;
import static org.springframework.http.HttpMethod.POST;

/**
 * 验证码过滤器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/30
 */
public class SecurityCaptchaFilter extends AbstractAuthenticationProcessingFilter {


	/**
	 * @param defaultFilterProcessesUrl the default value for <tt>filterProcessesUrl</tt>.
	 */
	public SecurityCaptchaFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	/**
	 * 过滤
	 *
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (LOGIN.equals(req.getServletPath()) && POST.name().equalsIgnoreCase(req.getMethod())) {
			String expect = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

			//销毁验证码
			req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

			if (!ObjectUtils.isEmpty(expect) && !expect.equals(req.getParameter(KAPTCHA_SESSION_KEY))) {
				logger.info("验证码错误!");
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write("{\"status\":\"captcha\",\"msg\":\"验证码错误\"}");
				out.flush();
				out.close();
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		return null;
	}
}
