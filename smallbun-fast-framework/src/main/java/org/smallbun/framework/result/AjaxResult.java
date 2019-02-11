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
 * AJAX请求返回结果工具类
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/5/1
 */

public class AjaxResult extends AbstractResult {

	/**
	 * 返回结果内容 ,为方便返回不同类型，这个使用Object类型
	 */
	private Object result;

	private AjaxResult(String status, String msg, Object result) {
		this.status = status;
		this.msg = msg;
		this.result = result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return result;
	}


	@Override
	public String toString() {
		return "AjaxResult{" + "result=" + result + ", status='" + status + '\'' + ", msg='" + msg + '\'' + '}';
	}

	public static AjaxResultBuilder builder() {
		return new AjaxResultBuilder();
	}

	public static class AjaxResultBuilder {
		private String status;
		private String msg;
		private Object result;

		AjaxResultBuilder() {
		}

		public AjaxResultBuilder status(String status) {
			this.status = status;
			return this;
		}

		public AjaxResultBuilder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public AjaxResultBuilder result(Object result) {
			this.result = result;
			return this;
		}


		public AjaxResult build() {
			if (StringUtils.isEmpty(status)) {
				status = Integer.toString(HttpStatus.OK.value());
			}
			if (StringUtils.isEmpty(msg)) {
				msg = HttpStatus.OK.getReasonPhrase();
			}
			return new AjaxResult(this.status, this.msg, this.result);
		}

		/**
		 * 加密构建
		 * @return
		 */
		public AjaxResult encryptBuild() {
			if (StringUtils.isEmpty(status)) {
				status = Integer.toString(HttpStatus.OK.value());
			}
			if (StringUtils.isEmpty(msg)) {
				msg = HttpStatus.OK.getReasonPhrase();
			}
			//进行加密操作

			return new AjaxResult(this.status, this.msg, this.result);
		}

		@Override
		public String toString() {
			return "AjaxResult.AjaxResultBuilder(status=" + this.status + ", msg=" + this.msg + ", data=" + this.result
					+ ")";
		}
	}
}
