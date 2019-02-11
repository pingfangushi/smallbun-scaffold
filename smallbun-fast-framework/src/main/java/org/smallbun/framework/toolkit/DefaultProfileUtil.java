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

package org.smallbun.framework.toolkit;


import org.smallbun.framework.auto.SmallBunDefaults;
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
		defProperties.put("smallbun.project.name", SmallBunDefaults.Project.name);
		defProperties.put("smallbun.project.version", SmallBunDefaults.Project.version);
		defProperties.put("smallbun.project.powered-by", SmallBunDefaults.Project.poweredBy);
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
