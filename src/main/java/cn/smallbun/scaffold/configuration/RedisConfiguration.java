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

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Redis配置
 * 2017年3月15日，FastJson 官方发布安全升级公告，该公告介绍 FastJson 在1.2.24及之前的版本存在代码执行漏洞，
 * 当恶意攻击者提交一个精心构造的序列化数据到服务端时，由于fastJson在反序列化时存在漏洞，可导致远程任意代码执行。
 * 自1.2.25及之后的版本，禁用了部分autoType的功能，也就是@type这种指定类型的功能会被限制在一定范围内使用。
 * 而由于反序列化对象时，需要检查是否开启了autoType。所以如果反序列化检查时，autoType没有开启，就会报错。
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/7/17 22:55
 */
@EnableCaching
@Configuration
public class RedisConfiguration {
	/**
	 * 配置 CacheManager 过期时间30天
	 * @param redisConnectionFactory  {@link RedisConnectionFactory}
	 * @return {@link CacheManager}
	 */
	@Primary
	@Bean(value = "cacheManager")
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		//获取Redis配置
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		//设置序列化方式
		configuration = configuration.serializeValuesWith(
				//value 序列化方式
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()))
				//设置过期时间30天
				.entryTtl(Duration.ofDays(30));
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(configuration).build();
	}

	/**
	 * 配置 CacheManager 一天
	 * @param redisConnectionFactory  {@link RedisConnectionFactory}
	 * @return {@link CacheManager}
	 */
	@Bean(value = "cacheManagerOneDay")
	public CacheManager cacheManagerOneDay(RedisConnectionFactory redisConnectionFactory) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		//获取Redis配置
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		//设置序列化方式
		configuration = configuration.serializeValuesWith(
				//value 序列化方式
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()))
				//设置过期时间1天
				.entryTtl(Duration.ofDays(1));
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(configuration).build();
	}

	/**
	 * 重写Redis序列化方式
	 * 在此我们将自己配置RedisTemplate并定义Serializer。
	 * @param redisConnectionFactory {@link RedisConnectionFactory}
	 * @return {@link RedisTemplate}
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

		//设置序列化方式
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericFastJsonRedisSerializer());

		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericFastJsonRedisSerializer());
		return template;
	}

	/**
	 * 配置 StringRedisTemplate
	 * @param redisConnectionFactory  {@link RedisConnectionFactory}
	 * @return {@link StringRedisTemplate}
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		//key配置
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		//value配置
		template.setValueSerializer(new GenericFastJsonRedisSerializer());
		template.setHashValueSerializer(new GenericFastJsonRedisSerializer());
		return template;
	}


}
