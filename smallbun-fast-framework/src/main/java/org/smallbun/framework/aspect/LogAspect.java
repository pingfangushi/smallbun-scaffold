
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
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.smallbun.framework.base.ILogLogic;
import org.smallbun.framework.toolkit.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public LogAspect(ILogLogic iLogLogic) {
		this.iLogLogic = iLogLogic;
	}

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

	@Pointcut("@annotation(org.smallbun.framework.annotation.LogAnnotation)")
	public void logPointCut() {
	}

	/**
	 * 前置通知 用于拦截操作
	 *
	 * @param joinPoint 切点
	 */
	@AfterReturning(pointcut = "logPointCut()")
	public void doAfter(JoinPoint joinPoint) {
		iLogLogic.operation(joinPoint, null);
	}

	/**
	 * LogAnnotation 日志处理
	 *
	 * @param joinPoint {@link JoinPoint}
	 */
	@AfterThrowing(value = "logPointCut()", throwing = "e")
	public void doAfter(JoinPoint joinPoint, Exception e) {
		iLogLogic.operation(joinPoint, e);
	}

	/**
	 * 日志逻辑接口
	 */
	private final ILogLogic iLogLogic;
}
