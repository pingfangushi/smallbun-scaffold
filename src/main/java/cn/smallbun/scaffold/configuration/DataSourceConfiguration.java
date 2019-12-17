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

import cn.smallbun.scaffold.framework.mybatis.interceptor.PaginationInterceptor;
import cn.smallbun.scaffold.framework.security.utils.SecurityUtils;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static cn.smallbun.scaffold.framework.mybatis.domain.BaseAuditNodeEntity.*;
import static cn.smallbun.scaffold.framework.security.domain.User.ANONYMOUS_USER;

/**
 * 数据源配置
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/5/9 11:07
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"cn.smallbun.scaffold.**.mapper"})
public class DataSourceConfiguration {

	public DataSourceConfiguration(MybatisPlusProperties mybatisPlusProperties) {
		this.mybatisPlusProperties = mybatisPlusProperties;
	}

	/**
	 * 分页插件
	 * @return {@link PaginationInterceptor}
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}


	/**
	 * 填充
	 * @return {@link MetaObjectHandler}
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MetaObjectHandler() {
			@Override
			public void insertFill(MetaObject metaObject) {
				Optional<String> optional = SecurityUtils.getCurrentUserId();
				//默认匿名用户
				String user = ANONYMOUS_USER;
				if (optional.isPresent()) {
					user = optional.get();
				}
				//新增
				this.strictInsertFill(metaObject, CREATE_BY, String.class, user);
				//新增
				this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class,
						LocalDateTime.now(ZoneId.systemDefault()));
				//修改
				this.strictInsertFill(metaObject, LAST_MODIFIED_BY, String.class, user);
				this.strictInsertFill(metaObject, LAST_MODIFIED_TIME, LocalDateTime.class,
						LocalDateTime.now(ZoneId.systemDefault()));
				//是否删除
				this.strictInsertFill(metaObject, IS_DELETED, String.class,
						mybatisPlusProperties.getGlobalConfig().getDbConfig().getLogicNotDeleteValue());
			}

			@Override
			public void updateFill(MetaObject metaObject) {
				Optional<String> optional = SecurityUtils.getCurrentUserId();
				//默认匿名用户
				String user = ANONYMOUS_USER;
				if (optional.isPresent()) {
					user = optional.get();
				}
				this.strictUpdateFill(metaObject, LAST_MODIFIED_BY, String.class, user);
				this.strictUpdateFill(metaObject, LAST_MODIFIED_TIME, LocalDateTime.class,
						LocalDateTime.now(ZoneId.systemDefault()));
			}
		};
	}

	/**
	 * 乐观锁
	 * @return {@link OptimisticLockerInterceptor}
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	private final MybatisPlusProperties mybatisPlusProperties;
}
