/*
 * Copyright [2018] [左庆港]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smallbun.framework.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.log4j.Log4j2;
import org.smallbun.framework.auto.SmallBunProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import static com.google.code.kaptcha.Constants.*;

/**
 * 验证码配置
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/5/11
 */
@Log4j2
@Configuration
public class CaptchaConfiguration {


	public CaptchaConfiguration(SmallBunProperties properties) {
		this.kaptcha = properties.getKaptcha();
	}

	/**
	 * 配置验证码
	 *
	 * @return DefaultKaptcha
	 */
	@Bean
	public DefaultKaptcha captchaProducer() {
		StopWatch watch = new StopWatch();
		DefaultKaptcha captchaProducer = new DefaultKaptcha();
		java.util.Properties properties = new java.util.Properties();
		properties.setProperty(KAPTCHA_BORDER, kaptcha.getBorder());
		properties.setProperty(KAPTCHA_IMAGE_WIDTH, kaptcha.getImageWidth());
		properties.setProperty(KAPTCHA_IMAGE_HEIGHT, kaptcha.getImageHeight());
		properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, kaptcha.getTextProducerFontColor());
		properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, kaptcha.getTextProducerFontSize());
		properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, kaptcha.getTextProducerImpl());
		properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, kaptcha.getTextProducerCharLength());
		properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, kaptcha.getTextProducerFontNames());
		Config config = new Config(properties);
		captchaProducer.setConfig(config);
		return captchaProducer;
	}

	/**
	 * 注入
	 */
	private final SmallBunProperties.DefaultKaptcha kaptcha;

}
