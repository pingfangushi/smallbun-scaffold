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

package org.smallbun.framework.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.smallbun.framework.toolkit.UserAgentUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.smallbun.framework.constant.AuthoritiesConstant.IS_PHONE_PATH;

/**
 * 判断访问类型拦截器
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/9/2
 */
@Slf4j
public class UserAgentInterceptor implements HandlerInterceptor {


	/**
	 * 请求之前
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("----------------------------------------------------------");
		log.debug("用户请求设备类型为:{}", UserAgentUtils.getDeviceType(request));
		//如果是PC通过
		if (UserAgentUtils.isComputer(request)) {
			log.debug("用户请求设备类型为PC进行放行");
			log.debug("----------------------------------------------------------");
			return true;
		}
		log.debug("用户请求设备类型不是PC设备进行提示跳转");
		//设置返回客户端的编码
		response.setContentType("text/html;charset=UTF-8");
		//重定向到手机访问页面
		response.sendRedirect(IS_PHONE_PATH);
		log.debug("----------------------------------------------------------");
		return false;

	}

	/**
	 * 请求之后
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 整个请求结束后
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
