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

package org.smallbun.fast.manage.log.operate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.smallbun.fast.manage.log.operate.dao.SysOperateLogMapper;
import org.smallbun.fast.manage.log.operate.entity.SysOperateLogEntity;
import org.smallbun.fast.manage.log.operate.service.SysOperateLogService;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.base.ILogLogic;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 操作日志记录 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/15 22:03
 */
@Slf4j
@Service
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLogMapper, SysOperateLogEntity>
		implements SysOperateLogService, ILogLogic {

	/**
	 * 操作日志逻辑
	 * @param joinPoint {@link JoinPoint}
	 */
	@Override
	public void operation(JoinPoint joinPoint) {
		log.debug("操作日志业务记录开始");
		SysOperateLogEntity log = new SysOperateLogEntity();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		//功能类型
		log.setAction(logAnnotation.action());
		//请求地址
		log.setOperateUrl(request.getRequestURL().toString());
		//标题
		log.setTitle(logAnnotation.model());
		//请求参数
		log.setOperateParam(request.getLocalAddr());
		this.saveOrUpdate(log);
	}
}
