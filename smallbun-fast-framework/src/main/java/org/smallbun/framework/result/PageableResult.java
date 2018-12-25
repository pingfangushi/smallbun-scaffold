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
