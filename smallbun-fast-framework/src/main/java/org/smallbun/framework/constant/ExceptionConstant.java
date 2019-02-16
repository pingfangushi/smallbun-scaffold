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

package org.smallbun.framework.constant;

/**
 * 错误代码定义
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/11
 */
public interface ExceptionConstant {
	/**
	 * 演示模式，不允许操作
	 */
	String DEMO_ERROR_MSG = "演示模式，不允许操作！";

	/**
	 * 拦截器不通过
	 */
	String EX999999 = "999999";
	/**
	 * 参数校验失败(为空、错值提示)
	 */
	String EX900000 = "900000";
	/**
	 * 系统异常
	 */
	String EX900001 = "900001";
	/**
	 * 获取配置信息错误
	 */
	String EX900002 = "900002";
	/**
	 * 参数校验错误
	 */
	String EX900003 = "900003";
	/**
	 * 未定义错误消息
	 */
	String EX900004 = "900004";
	/**
	 * 数字签名校验错误
	 */
	String EX900005 = "900005";
	/**
	 * 参数类型不对
	 */
	String EX900006 = "900006";
	/**
	 * APP客户端连接超时
	 */
	String EX888888 = "888888";
	/**
	 * APP客户端异地登陆
	 */
	String EX888889 = "888889";
	/**
	 * APP客户端异地登陆
	 */
	String EX888887 = "888887";

	/**
	 * 登陆错误次数超限，商户被冻结
	 */
	String EX000101 = "000101";
	/**
	 * 密码错误!
	 */
	String EX000102 = "000102";
}
