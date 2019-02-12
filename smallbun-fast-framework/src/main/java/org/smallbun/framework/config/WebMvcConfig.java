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

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.constant.SystemConstant;
import org.smallbun.framework.initialize.ContextInitListener;
import org.smallbun.framework.web.filter.CachingHttpHeadersFilter;
import org.smallbun.framework.web.interceptor.UserAgentInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.smallbun.framework.constant.AuthoritiesConstant.IS_PHONE_PATH;


/**
 * WebMvc Config配置
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/30
 */
@Slf4j
@Configuration
@ComponentScan(value = "org.smallbun")
public class WebMvcConfig extends RequestMappingHandlerMapping
		implements WebMvcConfigurer, ServletContextInitializer, WebMvcRegistrations {
	/**
	 * 配置拦截器
	 *
	 * @param registry {@link InterceptorRegistry}
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//判断访问设备类型拦截器
		registry.addInterceptor(new UserAgentInterceptor()).addPathPatterns("/**")
				//排除移动端受限页面
				.excludePathPatterns(IS_PHONE_PATH);
	}

	/**
	 * 添加静态资源映射地址
	 *
	 * @param registry {@link ResourceHandlerRegistry}
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").resourceChain(true)
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.resourceChain(true).addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}

	/**
	 * 配置默认Servlet处理
	 *
	 * @param configurer {@link DefaultServletHandlerConfigurer}
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		//启用
		configurer.enable();
	}


	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//1、定义一个convert转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//2、添加fastJson的配置信息
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				//Boolean字段如果为null,输出为false,而非null
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteDateUseDateFormat);
		//解决Long太长精度问题
		SerializeConfig serializeConfig = SerializeConfig.globalInstance;
		serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
		serializeConfig.put(Long.class, ToStringSerializer.instance);
		serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
		fastJsonConfig.setSerializeConfig(serializeConfig);
		//设置UTF-8
		fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);

		//3、在convert中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);
		//4、将convert添加到converters中
		converters.add(0, fastConverter);


	}


	/**
	 * 注册ContextInitListener
	 *
	 * @return {@link ContextInitListener}
	 */
	@Bean
	public ContextInitListener contextInitListener() {
		return new ContextInitListener();
	}


	/**
	 * 时间配置
	 *
	 * @param registry {@link FormatterRegistry}
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		registrar.registerFormatters(registry);
	}

	/**
	 * 启动
	 *
	 * @param servletContext {@link ServletContext}
	 * @throws ServletException
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if (env.getActiveProfiles().length != 0) {
			log.debug("使用配置文件的Web应用程序配置: {}", Arrays.toString(env.getActiveProfiles()));
		}
		EnumSet<DispatcherType> dispatcherTypes = EnumSet
				.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
		//如果是生产环境
		if (env.acceptsProfiles(SystemConstant.SPRING_PROFILE_PRODUCTION)) {
			//初始化缓存HTTP标头过滤器
			initCachingHttpHeadersFilter(servletContext, dispatcherTypes);
		}
		log.debug("Web应用程序已完全配置");
	}

	/**
	 * 初始化缓存HTTP标头过滤器
	 *
	 * @param servletContext {@link ServletContext}
	 * @param disps          {@link EnumSet<DispatcherType>}
	 */
	private void initCachingHttpHeadersFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
		log.debug("注册缓存HTTP标头过滤器");
		FilterRegistration.Dynamic cachingHttpHeadersFilter = servletContext
				.addFilter("cachingHttpHeadersFilter", new CachingHttpHeadersFilter(smallBunProperties));
		cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/webjars/*", "/static/*");
		cachingHttpHeadersFilter.setAsyncSupported(true);
	}


	@Resource
	private Environment env;
	@Resource
	private SmallBunProperties smallBunProperties;

}
