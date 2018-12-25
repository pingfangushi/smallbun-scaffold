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
