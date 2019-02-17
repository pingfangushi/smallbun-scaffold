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

package org.smallbun.fast.manage.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.smallbun.fast.common.utils.AutoMapperUtil;
import org.smallbun.fast.manage.log.dao.SysOperateLogMapper;
import org.smallbun.fast.manage.log.entity.SysOperateLogEntity;
import org.smallbun.fast.manage.log.service.SysOperateLogService;
import org.smallbun.fast.manage.log.vo.SysOperateLogVO;
import org.smallbun.framework.annotation.LogAnnotation;
import org.smallbun.framework.base.BaseEntity;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.base.ILogLogic;
import org.smallbun.framework.toolkit.IpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.smallbun.fast.manage.user.util.UserUtil.getUserId;
import static org.smallbun.fast.manage.user.util.UserUtil.getUserOrg;
import static org.smallbun.framework.constant.ExceptionConstant.EX900001;
import static org.smallbun.framework.constant.OperateLogConstant.*;
import static org.smallbun.framework.constant.SystemConstant.SUCCESS;
import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AddressUtil.getRealAddressByIP;
import static org.smallbun.framework.toolkit.ReflectionUtil.getFieldAll;

/**
 * 操作日志记录 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/15 22:03
 */
@Slf4j
@Service
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLogMapper, SysOperateLogEntity>
		implements SysOperateLogService, ILogLogic {

	@Override
	public SysOperateLogVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysOperateLogVO() :
					AutoMapperUtil.mapping(getById(request.getParameter(ID)), new SysOperateLogVO());
		}
		return new SysOperateLogVO();
	}

	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<SysOperateLogEntity> page(IPage<SysOperateLogEntity> page, SysOperateLogVO vo) {
		return baseMapper.page(page, vo);
	}


	/**
	 * 操作日志逻辑
	 * @param joinPoint {@link JoinPoint}
	 * @param e {@link  Exception}
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
		//功能类型，这里需要进行一次判断,如果是添加或修改form，或者添加修改的接口，参数实体类必须继承BaseEntity，根据id判断是新增还是修改
		if (logAnnotation.action().equals(OPEN_VIEW_FORM) || logAnnotation.action().equals(ADD_UPDATE)) {
			Object[] args = joinPoint.getArgs();
			for (Object u : args) {
				try {
					if (u instanceof BaseEntity) {
						Field id = getFieldAll(u, ID);
						id.setAccessible(true);

						//如果是form页面，为空说明是打开，id不为空是查看
						if (logAnnotation.action().equals(OPEN_VIEW_FORM)) {
							if (StringUtils.isEmpty(id.get(u))) {
								operateLog.setAction(OPEN_FORM);
							} else {
								operateLog.setAction(VIEW_FORM);
							}
						}
						//如果不是form页面，为空说明是打开，id不为空是查看
						if (logAnnotation.action().equals(ADD_UPDATE)) {
							if (StringUtils.isEmpty(id.get(u))) {
								operateLog.setAction(ADD);
							} else {
								operateLog.setAction(UPDATE);
							}
						}
					}
				} catch (Exception ignored) { }
			}
		} else {
			operateLog.setAction(logAnnotation.action());
		}
		saveOperateLog(joinPoint, e, operateLog, logAnnotation, request);
		log.debug("操作日志业务记录结束");
		log.debug("----------------------------------------------------------");

	}

	/**
	 * 保存
	 * @param joinPoint {@link  JoinPoint}
	 * @param e  {@link  Exception}
	 * @param operateLog {@link  SysOperateLogEntity}
	 * @param logAnnotation {@link  LogAnnotation}
	 * @param request {@link  HttpServletRequest}
	 */
	private void saveOperateLog(JoinPoint joinPoint, Exception e, SysOperateLogEntity operateLog,
			LogAnnotation logAnnotation, HttpServletRequest request) {
		//请求地址
		operateLog.setOperateUrl(request.getRequestURI());
		//标题
		operateLog.setTitle(logAnnotation.model());
		//请求参数
		if (logAnnotation.isSaveRequestData()) {
			operateLog.setOperateParam(JSON.toJSONString(request.getParameterMap()));
		}
		//渠道
		operateLog.setChannel(logAnnotation.operatorType());
		//方法名称
		operateLog.setMethod(
				joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
		//操作用户
		operateLog.setOperateUser(Objects.requireNonNull(getUserId()).toString());
		//部门
		operateLog.setOperateOrg(Objects.requireNonNull(Objects.requireNonNull(getUserOrg()).getId()).toString());
		//操作IP
		operateLog.setOperateIp(IpUtil.getIpAddr(request));
		//登录地点
		operateLog.setOperateLocation(getRealAddressByIP(IpUtil.getIpAddr(request)));
		//成功
		operateLog.setOperateStatus(SUCCESS);
		//异常
		if (!StringUtils.isEmpty(e)) {
			operateLog.setErrorMsg(e.toString());
			operateLog.setOperateStatus(EX900001);
		}
		//保存
		this.saveOrUpdate(operateLog);
	}
}
