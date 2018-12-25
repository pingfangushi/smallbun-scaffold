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

package org.smallbun.framework.toolkit;


import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 实用程序类加载Spring配置文件以用作默认配置文件
 * 当环境中没有设置<core> spring.profiles.active </ core>或者作为命令行参数时。
 * 如果该值在<core> application.yml </ core>中不可用，那么将使用<core> dev </ core>概要文件作为默认值。
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on 2018/5/10 13:30
 */
public final class DefaultProfileUtil {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

	private static final String SPRING_PROFILE_DEVELOPMENT = "dev";

	private DefaultProfileUtil() {
	}

	/**
	 * 设置未配置配置文件时使用的默认值。
	 *
	 * @param app the Spring application
	 */
	public static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>(16);
		/*
		 * 未定义其他配置文件时使用的默认配置文件
		 * 这不能在<core> application.yml </ core>文件中设置。
		 * 请参阅 https://github.com/spring-projects/spring-boot/issues/1219
		 */
		defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_DEVELOPMENT);
		app.setDefaultProperties(defProperties);
	}

	/**
	 * 获取应用的配置文件将获得默认配置文件。
	 *
	 * @param env spring environment
	 * @return profiles
	 */
	public static String[] getActiveProfiles(Environment env) {
		String[] profiles = env.getActiveProfiles();
		if (profiles.length == 0) {
			return env.getDefaultProfiles();
		}
		return profiles;
	}
}
