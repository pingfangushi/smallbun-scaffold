package org.smallbun.framework.auto;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/4 15:19
 */
@Log4j2
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(SmallBunProperties.class)
public class SmallBunAutoConfiguration {
	/**
	 * 注入 SmallBunProperties
	 */
	private final SmallBunProperties smallBunProperties;

	/**
	 * @param properties {@link SmallBunProperties}
	 */
	public SmallBunAutoConfiguration(SmallBunProperties properties) {
		this.smallBunProperties = properties;

	}
}
