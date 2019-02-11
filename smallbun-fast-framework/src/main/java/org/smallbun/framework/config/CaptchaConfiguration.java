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
