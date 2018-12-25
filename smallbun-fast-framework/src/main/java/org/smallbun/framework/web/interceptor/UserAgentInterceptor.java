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
