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

package org.smallbun.fast.common.interceptor;


import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallbun.framework.permission.annotation.DataScopeFilter;
import org.smallbun.framework.permission.util.PermissionUtil;

import java.io.StringReader;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

import static com.baomidou.mybatisplus.core.toolkit.PluginUtils.DELEGATE_BOUNDSQL_SQL;

/**
 * MyBatis 数据权限拦截器
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2018/11/14 -10:30
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PrepareInterceptor implements Interceptor {
	/** 日志 */
	private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("------------------------数据权限过滤开始------------------------");
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		if (statementHandler instanceof RoutingStatementHandler) {
			MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
			MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
			//如果没有@DataScopeFilter注解，说明不进行权限拦截，放行
			DataScopeFilter filterPermission = PermissionUtil.getPermissionByDelegate(mappedStatement);
			if (Objects.isNull(filterPermission)) {
				return invocation.proceed();
			}
			//根据当前用户的数据范围拼接SQL语句
			metaObject.setValue(DELEGATE_BOUNDSQL_SQL,
					permissionSql((String) metaObject.getValue(DELEGATE_BOUNDSQL_SQL)));
		}
		log.info("------------------------数据权限过滤结束------------------------");
		return invocation.proceed();


	}

	/**
	 * 权限sql包装
	 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
	 * Created by 2689170096@qq.com on  2018/11/14 -10:26
	 */
	private String permissionSql(String sql) {
		try {
			CCJSqlParserManager parserManager = new CCJSqlParserManager();
			Select select = (Select) parserManager.parse(new StringReader(sql));
			select.getSelectBody().accept(new SelectVisitorImpl());
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		return sql;
	}


}
