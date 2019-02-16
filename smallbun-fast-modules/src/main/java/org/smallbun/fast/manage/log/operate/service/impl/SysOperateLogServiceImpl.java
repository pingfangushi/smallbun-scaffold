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

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.smallbun.fast.manage.log.operate.dao.SysOperateLogMapper;
import org.smallbun.fast.manage.log.operate.entity.SysOperateLogEntity;
import org.smallbun.fast.manage.log.operate.service.SysOperateLogService;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.base.ILogLogic;
import org.smallbun.framework.toolkit.IpUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.smallbun.fast.manage.user.util.UserUtil.getUserId;
import static org.smallbun.fast.manage.user.util.UserUtil.getUserOrg;
import static org.smallbun.framework.constant.ExceptionConstant.EX900001;
import static org.smallbun.framework.constant.SystemConstant.SUCCESS;
import static org.smallbun.framework.toolkit.AddressUtil.getRealAddressByIP;

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
	 * @param joinPoint
	 * @param e
	 */
	@Override
	public void operation(JoinPoint joinPoint, Exception e) {
		SysOperateLogEntity operateLog = new SysOperateLogEntity();
		log.debug("----------------------------------------------------------");
		log.debug("操作日志业务记录开始");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		//操作时间
		operateLog.setOperateTime(LocalDateTime.now());
		//功能类型，这里需要进行一次判断
		operateLog.setAction(logAnnotation.action());
		//请求地址
		operateLog.setOperateUrl(request.getRequestURI());
		//标题
		operateLog.setTitle(logAnnotation.model());
		//请求参数
		operateLog.setOperateParam(JSON.toJSONString(joinPoint.getArgs()));
		//方法名称
		operateLog.setMethod(
				joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
		//操作用户
		operateLog.setOpenUser(Objects.requireNonNull(getUserId()).toString());
		//部门
		operateLog.setOpenOrg(Objects.requireNonNull(Objects.requireNonNull(getUserOrg()).getId()).toString());
		//操作IP
		operateLog.setOperateIp(getRealAddressByIP(IpUtil.getIpAddr(request)));
		//登录地点
		operateLog.setOperateLocation(getRealAddressByIP(IpUtil.getIpAddr(request)));
		//成功
		operateLog.setOperateStatus(SUCCESS);
		//异常
		if (e != null) {
			operateLog.setErrorMsg(e.toString());
			operateLog.setOperateStatus(EX900001);
		}
		//保存
		this.saveOrUpdate(operateLog);
		log.debug("操作日志业务记录结束");
		log.debug("----------------------------------------------------------");

	}
}
