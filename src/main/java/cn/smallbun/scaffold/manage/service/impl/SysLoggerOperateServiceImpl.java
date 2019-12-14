/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import cn.smallbun.scaffold.manage.entity.SysLoggerOperateEntity;
import cn.smallbun.scaffold.manage.mapper.SysLoggerOperateMapper;
import cn.smallbun.scaffold.manage.service.ISysLoggerOperateService;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author SanLi Automatic generated
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019-11-02
 */
@Service
public class SysLoggerOperateServiceImpl extends BaseServiceImpl<SysLoggerOperateMapper, SysLoggerOperateEntity>
		implements ISysLoggerOperateService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 记录数据
	 * @param log {@link Logger}
	 * @param joinPoint {@link JoinPoint}
	 */
	@Override
	public void recording(cn.smallbun.scaffold.framework.logger.domain.Logger log, JoinPoint joinPoint) {
		logger.debug("save operation record start...");
		save(getLog(log));
		logger.debug("end of save operation record.");
	}

	/**
	 * 获取Log
	 * @param log {@link Logger} log
	 * @return {@link SysLoggerOperateEntity}
	 */
	private SysLoggerOperateEntity getLog(cn.smallbun.scaffold.framework.logger.domain.Logger log) {
		//记录日志
		SysLoggerOperateEntity logger = new SysLoggerOperateEntity();
		//请求地址
		logger.setUri(log.getUri());
		//模块
		logger.setModule(log.getModule());
		//功能
		logger.setFeature(log.getFeature());
		//请求参数
		logger.setParams(log.getParams());
		//平台类型
		logger.setPlatform(log.getPlatform().getCode());
		//操作类型
		logger.setAction(log.getAction().getCode());
		//方法名称
		logger.setMethod(log.getMethod());
		//操作用户
		logger.setUser(log.getUser());
		//操作IP
		logger.setIp(log.getIp());
		//登录地点
		logger.setLocation(log.getLocation());
		//浏览器
		logger.setBrowser(log.getBrowser());
		//操作系统
		logger.setOs(log.getOs());
		//操作时间
		logger.setTime(log.getTime());
		//结果
		try {
			logger.setResult(JSON.toJSONString(log.getResult()));
		} catch (Exception e) {
			logger.setResult(log.getResult().toString());
		}
		//状态
		logger.setStatus(log.getStatus().getCode());
		return logger;
	}
}
