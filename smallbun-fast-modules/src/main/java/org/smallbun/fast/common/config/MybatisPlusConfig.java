/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.fast.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.smallbun.fast.common.handler.BaseMetaObjectHandler;
import org.smallbun.framework.injector.DataScopeSqlInjector;
import org.smallbun.fast.common.interceptor.PrepareInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置mybatis plus
 * @author SanLi
 */
@EnableTransactionManagement
@Configuration
@MapperScan(value = "org.smallbun.fast.**.dao")
public class MybatisPlusConfig {
	/**
	 * SQL执行效率插件 设置 dev test 环境开启
	 * @return {@link PerformanceInterceptor}
	 */
	@Bean
	@Profile({"dev", "test", "prod"})
	public PerformanceInterceptor  performanceInterceptor() {
		return new PerformanceInterceptor();
	}


	/**
	 * 配置数据权限过滤
	 * @return {@link DataScopeSqlInjector}
	 */
	@Bean
	public ISqlInjector dataScopeSqlInjector() {
		return new DataScopeSqlInjector();
	}

	/**
	 * 分页插件分页插件
	 * @return {@link PaginationInterceptor}
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	public PrepareInterceptor prepareInterceptor() {
		return new PrepareInterceptor();
	}


	/**
	 * 配置 MetaObjectHandler
	 * @return {@link MetaObjectHandler}
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new BaseMetaObjectHandler();
	}

}
