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

package cn.smallbun.scaffold.configuration;

import cn.smallbun.scaffold.framework.logger.aspect.LoggingAspect;
import cn.smallbun.scaffold.manage.service.ISysLoggerOperateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志切面配置
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/5/8 20:29
 */
@Configuration
public class LogAspectConfiguration {

	public LogAspectConfiguration(ISysLoggerOperateService service) {
		this.iSysLoggerService = service;
	}

	/**
	 * 配置 loggingAspect
	 * @return {@link LoggingAspect}
	 */
	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect(iSysLoggerService);
	}

	/**
	 * 注入 LogService
	 */
	private final ISysLoggerOperateService iSysLoggerService;
}
