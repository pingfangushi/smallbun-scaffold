/*
 * Copyright (c) 2019.  ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
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

import cn.smallbun.scaffold.manage.mapper.SysLoggerOperateMapper;
import com.alibaba.fastjson.JSON;
import cn.smallbun.scaffold.framework.logging.Log;
import cn.smallbun.scaffold.framework.mybatis.service.BaseServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.smallbun.scaffold.manage.entity.SysLoggerOperateEntity;
import cn.smallbun.scaffold.manage.service.ISysLoggerOperateService;
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
	 * @param log {@link Log}
	 * @param joinPoint {@link JoinPoint}
	 */
	@Override
	public void recording(Log log, JoinPoint joinPoint) {
		logger.debug("save operation record start...");
		save(getLog(log));
		logger.debug("end of save operation record.");
	}

	/**
	 * 获取Log
	 * @param log {@link Log} log
	 * @return {@link SysLoggerOperateEntity}
	 */
	private SysLoggerOperateEntity getLog(Log log) {
		//记录日志
		SysLoggerOperateEntity logging = new SysLoggerOperateEntity();
		//请求地址
		logging.setUri(log.getUri());
		//模块
		logging.setModule(log.getModule());
		//功能
		logging.setFeature(log.getFeature());
		//请求参数
		logging.setParams(log.getParams());
		//平台类型
		logging.setPlatform(log.getPlatform().getCode());
		//操作类型
		logging.setAction(log.getAction().getCode());
		//方法名称
		logging.setMethod(log.getMethod());
		//操作用户
		logging.setUser(log.getUser());
		//操作IP
		logging.setIp(log.getIp());
		//登录地点
		logging.setLocation(log.getLocation());
		//浏览器
		logging.setBrowser(log.getBrowser());
		//操作系统
		logging.setOs(log.getOs());
		//操作时间
		logging.setTime(log.getTime());
		//结果
		try {
			logging.setResult(JSON.toJSONString(log.getResult()));
		} catch (Exception e) {
			logging.setResult(log.getResult().toString());
		}
		//状态
		logging.setStatus(log.getStatus().getCode());
		return logging;
	}
}
