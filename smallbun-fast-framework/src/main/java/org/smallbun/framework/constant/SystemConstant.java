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
 * 系统常量
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
public class SystemConstant {
	/**
	 * 成功
	 */
	public static final String SUCCESS = "200";
	/**
	 * 默认错误处理页面
	 */
	public static final String DEFAULT_ERROR_VIEW = "/error/500";
	/**
	 * 系统错误默认提示
	 */
	public static final String DEFAULT_ERROR_MESSAGE = "系统开小差了，请稍后重试！";
	/**
	 * 正常
	 */
	public static final Integer VALID = 0;
	/**
	 * 禁用
	 */
	public static final Integer DISABLED = 1;

	/**
	 * 锁定
	 */
	public static final Integer LOCKED = 2;
	/**
	 * HTTP
	 */
	public static final String HTTP = "http";
	/**
	 * HTTPS
	 */
	public static final String HTTPS = "https";

	/**
	 * 用户名
	 */
	public static final String USER_NAME = "username";
	/**
	 * 私钥名称
	 */
	public static final String PRIVATE_KEY = "PRIVATE_KEY";
	/**
	 * 公钥名称
	 */
	public static final String PUBLIC_KEY = "PUBLIC_KEY";
	/**
	 * 秘钥存储名称
	 */
	public static final String PD_CURRENT_RSA_KEY = "PD_CURRENT_RSA_KEY";
	/**
	 * 开发环境
	 */
	public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
	/**
	 * 测试环境
	 */
	public static final String SPRING_PROFILE_TEST = "test";
	/**
	 * 生产环境
	 */
	public static final String SPRING_PROFILE_PRODUCTION = "prod";
	/**
	 * 云
	 */
	public static final String SPRING_PROFILE_CLOUD = "cloud";
	/**
	 * heroku
	 */
	public static final String SPRING_PROFILE_HEROKU = "heroku";
	/**
	 * swagger
	 */
	public static final String SPRING_PROFILE_SWAGGER = "swagger";
	/**
	 * liquibase
	 */
	public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";
	/**
	 * k8s
	 */
	public static final String SPRING_PROFILE_K8S = "k8s";
	/**
	 * SSL
	 */
	public static final String SERVER_SSL_KEY_STORE = "server.ssl.key-store";


}
