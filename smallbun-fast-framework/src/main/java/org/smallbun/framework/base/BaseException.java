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
