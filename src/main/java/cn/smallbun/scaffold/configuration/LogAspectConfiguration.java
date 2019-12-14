package cn.smallbun.scaffold.configuration;

import cn.smallbun.scaffold.framework.logging.aspect.LoggingAspect;
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
