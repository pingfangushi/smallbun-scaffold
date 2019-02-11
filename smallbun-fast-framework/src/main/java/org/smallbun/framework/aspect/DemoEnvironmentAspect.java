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

package org.smallbun.framework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.exception.BusinessExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.smallbun.framework.constant.ExceptionConstant.DEMO_ERROR_MSG;

/**
 * 演示环境切面
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/3 22:46
 */
@Slf4j
@Aspect
@Component
public class DemoEnvironmentAspect {

	private final SmallBunProperties smallBunProperties;

	@Autowired
	public DemoEnvironmentAspect(SmallBunProperties smallBunProperties) {
		this.smallBunProperties = smallBunProperties;
	}

	/**
	 * 演示环境切面
	 * @param pjp
	 * @param demoEnvironment
	 * @return
	 */
	@Around("@annotation(demoEnvironment)")
	public Object demo(ProceedingJoinPoint pjp, DemoEnvironment demoEnvironment) throws Throwable {
		//1.如果系统没开启演示环境，忽略注解
		//2.如果系统整体开启了，根据注解值进行判断是否拦截并提示，演示环境不允许操作
		if (smallBunProperties.getDemo().isOpen()) {
			boolean open = demoEnvironment.open();
			if (open) {
				log.info("---------------------------演示环境拦截---------------------------");
				throw new BusinessExecption(String.valueOf(HttpStatus.FORBIDDEN), DEMO_ERROR_MSG);
			}
		}
		return pjp.proceed();
	}
}
