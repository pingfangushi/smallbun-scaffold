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

package org.smallbun.framework.result;


import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * 数据表格分页查询返回工具
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/5/2
 */
public class PageableResult extends AbstractResult {
	/**
	 * 查询出的分页对象(Object目的是为了可以传入任何Page)
	 */
	private Object page;

	private PageableResult(String status, String msg, Object page) {
		this.status = status;
		this.msg = msg;
		this.page = page;
	}

	public Object getPage() {
		return page;
	}

	public void setPage(Object page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "PageableResult{" + "page=" + page + ", status='" + status + '\'' + ", msg='" + msg + '\'' + '}';
	}

	public static PageableResultBuilder builder() {
		return new PageableResultBuilder();
	}

	public static class PageableResultBuilder {
		private String status;
		private String msg;
		private Object page;

		PageableResultBuilder() {
		}

		public PageableResultBuilder status(String status) {
			this.status = status;
			return this;
		}

		public PageableResultBuilder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public PageableResultBuilder page(Object page) {
			this.page = page;
			return this;
		}

		public PageableResult build() {
			/**
			 * 如果未设置状态码，状态码为
			 */
			if (StringUtils.isEmpty(status)) {
				status = Integer.toString(HttpStatus.OK.value());
			}
			if (StringUtils.isEmpty(msg)) {
				msg = HttpStatus.OK.getReasonPhrase();
			}
			return new PageableResult(this.status, this.msg, this.page);
		}

		@Override
		public String toString() {
			return "PageableResult.PageableResultBuilder(status=" + this.status + ", msg=" + this.msg + ", page="
					+ this.page + ")";
		}
	}
}
