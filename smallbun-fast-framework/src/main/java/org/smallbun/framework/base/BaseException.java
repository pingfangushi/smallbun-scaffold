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
package org.smallbun.framework.base;

import lombok.extern.slf4j.Slf4j;


/**
 * 自定义异常基类
 *
 * @author SanLi
 * Created by 2689170096@qq.com  on 2018/1/9
 */
@Slf4j
public abstract class BaseException extends RuntimeException {

	/**
	 * 异常状态码
	 */
	private String status;
	/**
	 * 错误信息
	 */
	private String msg;

	public BaseException(String status, String msg) {
		super(msg);
		this.status = status;
		this.msg = msg;
		log.error("异常原因(status:{},msg:{})", status, msg);
	}

	public BaseException(String message, String status, String msg) {
		super(message);
		this.status = status;
		this.msg = msg;
	}

	public BaseException(String message, Throwable cause, String status, String msg) {
		super(message, cause);
		this.status = status;
		this.msg = msg;
	}

	public BaseException(Throwable cause, String status, String msg) {
		super(cause);
		this.status = status;
		this.msg = msg;
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			String status, String msg) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.status = status;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
