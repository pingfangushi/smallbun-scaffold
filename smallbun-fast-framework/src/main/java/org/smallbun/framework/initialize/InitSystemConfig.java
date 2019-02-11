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

