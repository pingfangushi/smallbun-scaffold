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

package org.smallbun.framework.constant;

/**
 * 系统常量
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/4/30
 */
public class SystemConstant {


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
