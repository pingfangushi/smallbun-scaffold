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

package org.smallbun.framework.web.filter;


import org.smallbun.framework.auto.SmallBunProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 前端静态资源缓存过滤器
 * 过滤器用于生产，将HTTP缓存标头放置一个很长的（1个月）到期时间。
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/9/3
 */
public class CachingHttpHeadersFilter implements Filter {

	/**
	 * 我们认为最后修改日期是服务器的启动时间
	 */
	private static final long LAST_MODIFIED = System.currentTimeMillis();

	private long cacheTimeToLive = TimeUnit.DAYS.toMillis(1461L);

	private SmallBunProperties smallBunProperties;

	public CachingHttpHeadersFilter(SmallBunProperties smallBunProperties) {
		this.smallBunProperties = smallBunProperties;
	}

	@Override
	public void init(FilterConfig filterConfig) {
		cacheTimeToLive = TimeUnit.DAYS.toMillis(smallBunProperties.getHttp().getCache().getTimeToLiveInDays());
	}

	@Override
	public void destroy() {
		// Nothing to destroy
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//设置缓存时间
		httpResponse.setHeader("Cache-Control", "max-age=" + cacheTimeToLive + ", public");
		httpResponse.setHeader("Pragma", "cache");

		// 设置Expires标头，用于代理缓存
		httpResponse.setDateHeader("Expires", cacheTimeToLive + System.currentTimeMillis());

		// 设置Last-Modified标头，用于浏览器缓存
		httpResponse.setDateHeader("Last-Modified", LAST_MODIFIED);

		chain.doFilter(request, response);
	}
}
