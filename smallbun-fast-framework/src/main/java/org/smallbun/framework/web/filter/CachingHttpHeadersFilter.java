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
