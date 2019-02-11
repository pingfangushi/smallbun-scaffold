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
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Spring容器加载完毕,用于初始化操作
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/9/3
 */
@Slf4j
public class ContextInitListener implements ApplicationListener<ContextRefreshedEvent> {
	/**
	 * 初始化方法名称
	 */
	private static final String INIT = "initialize";

	@Override
	public void onApplicationEvent(@Nullable ContextRefreshedEvent contextRefreshedEvent) {

		log.debug("------------------------初始化带有 @Initialize 注解方法开始------------------------");
		// spring初始化完毕后，通过反射调用所有使用Initialize注解的方法
		assert contextRefreshedEvent != null;

		Map<String, Object> initServices = contextRefreshedEvent.getApplicationContext()
				.getBeansWithAnnotation(Initialize.class);
		if (!CollectionUtils.isEmpty(initServices)) {
			for (Object service : initServices.values()) {
				log.debug("------------------------执行带有 @Initialize 注解方法 {}------------------------",
						service.getClass().getName());
				try {
					Method initMapper = service.getClass().getMethod("afterPropertiesSet");
					initMapper.invoke(service);
				} catch (Exception e) {
					log.error("------------------------初始化带有 @Initialize 注解方法异常{}------------------------", e);
					e.printStackTrace();
				}
			}
		}
		log.debug("------------------------初始化带有 @Initialize 注解方法方法结束------------------------");
		// Spring 框架初始化完毕后，通过反射调用所有实现 InitInterface 接口的 initialize 方法
		log.debug("------------------------初始化 InitInterface 接口 initialize()方法开始------------------------");
		Map<String, InitInterface> initInterface = contextRefreshedEvent.getApplicationContext()
				.getBeansOfType(InitInterface.class);
		if (!CollectionUtils.isEmpty(initInterface)) {
			for (Object service : initInterface.values()) {
				log.debug("------------------------执行 InitInterface{}.initialize()------------------------",
						service.getClass().getName());
				try {
					Method init = service.getClass().getMethod(INIT);
					init.invoke(service);
				} catch (Exception e) {
					log.error("------------------------初始化 InitInterface 接口 initialize()方法异常{}------------------------", e);
					e.printStackTrace();
				}
			}
		}
		log.debug("------------------------初始化 InitInterface 接口 initialize()方法结束------------------------");
	}


}
