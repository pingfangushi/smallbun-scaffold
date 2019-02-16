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

package org.smallbun.framework.annotation;

import java.lang.annotation.*;

import static org.smallbun.framework.constant.OperateLogConstant.MANAGE;

/**
 * 日志注解
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/7
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface LogAnnotation {
	/**
	 * 模块
	 *
	 * @return {@link String}
	 */
	String model();

	/**
	 * 操作类型
	 * @return
	 */
	String action();

	/**
	 * 操作人类别
	 */
	String operatorType() default MANAGE;

	/**
	 * 是否保存请求的参数
	 */
	boolean isSaveRequestData() default true;
}
