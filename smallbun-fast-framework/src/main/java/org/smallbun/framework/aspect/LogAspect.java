
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

package org.smallbun.framework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.smallbun.framework.toolkit.UserAgentUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * AOP 请求Controller打印基本信息
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/30
 */
@Slf4j
@Order(value = 0)
@Aspect
@Component
public class LogAspect {

	/**
	 * 配置切面
	 */
	@Pointcut(value = "execution(* *.smallbun.fast.*.*.*(..)) || execution(* *.smallbun.fast.*.*.controller.*.*(..))")
	public void log() {
	}

	/**
	 * 请求Controller 日志处理
	 *
	 * @param joinPoint {@link JoinPoint}
	 */
	@Before("log()")
	public void logDeBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();
		log.info("----------------------------------------------------------");
		log.info("用户代理:{}", UserAgentUtils.getUserAgent(request));
		log.info("请求路径:{}", request.getRequestURL().toString());
		log.info("请求类型:{}", request.getMethod());
		log.info("客户端IP:{}", request.getRemoteAddr());
		log.info("请求方法:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		log.info("请求参数:{}", Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * 之后操作
	 *
	 * @param returnValue {@link Object}
	 */
	@AfterReturning(pointcut = "log()", returning = "returnValue")
	public void logDoAfterReturning(Object returnValue) {
		if (StringUtils.isEmpty(returnValue)) {
			returnValue = "";
		}
		log.info("请求响应:{}", returnValue);
		log.info("----------------------------------------------------------");
	}


	/**
	 * =================================================================================
	 *
	 * 								      配置日志注解逻辑
	 *
	 * =================================================================================
	 */


	/**
	 * 配置带有@SystemLog注解的切面
	 */
	@Pointcut("@annotation(org.smallbun.framework.annotation.SystemLog)")
	public void logAnnotation() {
	}

	/**
	 * SystemLog 日志处理
	 *
	 * @param joinPoint {@link JoinPoint}
	 */
	@Before(value = "logAnnotation()")
	public void logAnnotationDeBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();

	}

	/**
	 * 之后操作
	 *
	 * @param returnValue {@link Object}
	 */
	@AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
	public void logAnnotationDoAfterReturning(Object returnValue) {
		if (StringUtils.isEmpty(returnValue)) {
			returnValue = "";
		}
	}


}
