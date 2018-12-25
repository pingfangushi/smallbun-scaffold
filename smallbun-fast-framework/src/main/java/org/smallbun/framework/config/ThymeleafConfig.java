package org.smallbun.framework.config;

import org.smallbun.framework.thymeleaf.dialect.SmallBunDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.AbstractProcessorDialect;

/**
 * Thymeleaf
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/29 21:19
 */
@Configuration
public class ThymeleafConfig {
	/**
	 * 配置自定义字典
	 *
	 * @return
	 */
	@Bean
	public AbstractProcessorDialect abstractProcessorDialect() {
		return new SmallBunDialect();
	}
}
