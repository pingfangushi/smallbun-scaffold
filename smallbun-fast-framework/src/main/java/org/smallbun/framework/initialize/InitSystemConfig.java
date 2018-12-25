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

package org.smallbun.framework.initialize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * 初始化系统配置
 * <p>
 * 如果有多个这样的类时，可以通过Order指定执行顺序，数值越小执行优先级越高@Order(value = 0)
 * </p>
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/6
 */
@Slf4j
@Component
public class InitSystemConfig implements CommandLineRunner, EnvironmentAware {
	/**
	 * 在服务启动后执行，会在@Bean实例化之后执行，故如果@Bean需要依赖这里的话会出问题
	 *
	 * @param args ${@link String}
	 */
	@Override
	public void run(String... args) {
		//这里可以根据数据库返回结果创建一些对象、启动一些线程等

	}

	/**
	 * 在@Bean实例化之前执行常用于读取数据库配置以供其它bean使用
	 * environment对象可以获取配置文件的配置，也可以把配置设置到该对象中
	 *
	 * @param environment ${@link Environment }
	 */
	@Override
	public void setEnvironment(@Nullable Environment environment) {

	}
}

