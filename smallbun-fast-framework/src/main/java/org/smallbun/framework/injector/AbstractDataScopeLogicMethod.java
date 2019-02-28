/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.smallbun.framework.injector;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

import java.util.Arrays;

import static com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils.convertWhere;
import static java.util.stream.Collectors.joining;
import static org.smallbun.framework.toolkit.SqlScriptUtil.convertWhen;

/**
 * <p>
 * 抽象的注入方法类
 * </p>
 *
 * @author hubin
 * @since 2018-04-06
 */
public abstract class AbstractDataScopeLogicMethod extends AbstractMethod {
	/**
	 * 别名
	 */
	public static final String ALIAS = "a";

	/**
	 * 用户权限类型
	 */
	public static final String ENTITY_PERMISSION_TYPE = WRAPPER_ENTITY + DOT + "permissionType";

	public AbstractDataScopeLogicMethod() {
		// TO DO NOTHING
	}

	/**
	 * <p>
	 * SQL 更新 set 语句
	 * </p>
	 *
	 * @param table 表信息
	 * @return sql set 片段
	 */
	protected String sqlLogicSet(TableInfo table) {
		return "SET " + table.getLogicDeleteSql(false, true);
	}

	// ------------ 处理逻辑删除条件过滤 ------------

	@Override
	protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
		if (table.isLogicDelete()) {
			String sqlScript = getAllSqlWhere(table);
			//SQL
			sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
			sqlScript += (NEWLINE + getLogicDeleteSql(table, true, false) + NEWLINE);
			String normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", WRAPPER_SQLSEGMENT),
					String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
							WRAPPER_NONEMPTYOFNORMAL), true);
			normalSqlScript += NEWLINE;
			normalSqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
					String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
							WRAPPER_EMPTYOFNORMAL), true);
			sqlScript += normalSqlScript;
			/**
			 * 权限(逻辑删除)
			 */
			String permissionSqlScript = NEWLINE.concat(SqlScriptUtils
					.convertIf("", String.format("%s != null and %s != ''", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT),
							true));
			sqlScript += permissionSqlScript;
			System.err.println("=======" + sqlScript);
			sqlScript = SqlScriptUtils.convertChoose(String.format("%s != null", WRAPPER), sqlScript,
					getLogicDeleteSql(table, false, false));
			//生成where脚本标签
			sqlScript = convertWhere(sqlScript);
			return newLine ? NEWLINE + sqlScript : sqlScript;
		}
		// 正常逻辑
		return super.sqlWhereEntityWrapper(newLine, table);
	}

	@Override
	protected String sqlWhereByMap(TableInfo table) {
		if (table.isLogicDelete()) {
			// 逻辑删除
			String sqlScript = SqlScriptUtils.convertChoose("v == null", " ${k} IS NULL ", " ${k} = #{v} ");
			sqlScript = SqlScriptUtils.convertForeach(sqlScript, "cm", "k", "v", "AND");
			sqlScript = SqlScriptUtils.convertIf(sqlScript, "cm != null and !cm.isEmpty", true);
			sqlScript += (NEWLINE + table.getLogicDeleteSql(true, false));
			sqlScript = convertWhere(sqlScript);
			return sqlScript;
		}
		return super.sqlWhereByMap(table);
	}

	@Override
	protected String sqlSelectColumns(TableInfo table, boolean queryWrapper) {
		/* 假设存在 resultMap 映射返回 */
		String selectColumns = ASTERISK;
		if (table.getResultMap() == null) {
			/* 普通查询 */
			selectColumns = table.getAllSqlSelect();
			//添加别名
			String[] split = selectColumns.split(COMMA);
			StringBuilder sqlBuilder = new StringBuilder();
			Arrays.stream(split).forEach(u -> sqlBuilder.append(ALIAS).append(DOT).append(u).append(COMMA));
			selectColumns = sqlBuilder.substring(0, sqlBuilder.length() - 1);
		}
		if (!queryWrapper) {
			return selectColumns;
		}
		return SqlScriptUtils.convertChoose(String.format("%s != null and %s != null", WRAPPER, Q_WRAPPER_SQL_SELECT),
				SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_SELECT), selectColumns);
	}

	/**
	 * 获取所有的查询的 sql 片段
	 * @param tableInfo
	 * @return sql 脚本片段
	 */
	private String getAllSqlWhere(TableInfo tableInfo) {
		String filedSqlScript = tableInfo.getFieldList().stream()
				.filter(i -> !(tableInfo.isLogicDelete() && i.isLogicDelete())).map(this::getSqlWhere)
				.collect(joining(NEWLINE));
		if (StringUtils.isEmpty(tableInfo.getKeyProperty())) {
			return filedSqlScript;
		}
		String newKeyProperty =
				com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER_ENTITY_DOT + tableInfo.getKeyProperty();
		String keySqlScript = tableInfo.getKeyColumn() + EQUALS + SqlScriptUtils.safeParam(newKeyProperty);
		return SqlScriptUtils.convertIf(keySqlScript, String.format("%s != null", newKeyProperty), false) + NEWLINE
				+ filedSqlScript;
	}

	/**
	 * 获取 查询的 sql 片段
	 *
	 * @return sql 脚本片段
	 */
	private String getSqlWhere(TableFieldInfo fieldInfo) {
		final String newPrefix = "ew.entity.";
		// 默认:  AND column=#{prefix + el}
		String sqlScript = " AND " + String
				.format(fieldInfo.getCondition(), ALIAS.concat(DOT).concat(fieldInfo.getColumn()),
						newPrefix + fieldInfo.getEl());
		// 查询的时候只判非空
		return convertIf(fieldInfo, sqlScript, newPrefix + fieldInfo.getProperty());
	}

	/**
	 * 转换成 if 标签的脚本片段
	 *
	 * @param sqlScript sql 脚本片段
	 * @param property  字段名
	 * @return if 脚本片段
	 */
	private String convertIf(TableFieldInfo fieldInfo, final String sqlScript, final String property) {
		if (fieldInfo.getFieldStrategy() == FieldStrategy.IGNORED) {
			return sqlScript;
		}
		if (fieldInfo.getFieldStrategy() == FieldStrategy.NOT_EMPTY && fieldInfo.isCharSequence()) {
			return SqlScriptUtils
					.convertIf(sqlScript, String.format("%s != null and %s != ''", property, property), false);
		}
		return SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", property), false);
	}

	/**
	 * 获取逻辑删除字段的 sql 脚本
	 *
	 * @param startWithAnd 是否以 and 开头
	 * @param deleteValue  是否需要的是逻辑删除值
	 * @return sql 脚本
	 */
	public String getLogicDeleteSql(TableInfo tableInfo, boolean startWithAnd, boolean deleteValue) {
		if (tableInfo.isLogicDelete()) {
			TableFieldInfo field = tableInfo.getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst()
					.orElseThrow(() -> ExceptionUtils.mpe("can't find the logicFiled from table {%s}",
							tableInfo.getTableName().concat(SPACE).concat(ALIAS)));
			String formatStr = field.isCharSequence() ? "'%s'" : "%s";
			String logicDeleteSql = field.getColumn() + EQUALS + String
					.format(formatStr, deleteValue ? field.getLogicDeleteValue() : field.getLogicNotDeleteValue());
			//表别名
			logicDeleteSql = ALIAS.concat(DOT).concat(logicDeleteSql);
			if (startWithAnd) {
				logicDeleteSql = " AND " + logicDeleteSql;
			}
			return logicDeleteSql;
		}
		return EMPTY;
	}

	/**
	 * 获取权限连接SQL 片段
	 * @return {@link String}
	 */
	protected String getPermissionConnection() {
		//返回权限条件连接
		return convertWhen(
				"LEFT JOIN SYS_USER su on a.CREATOR=su.ID" + NEWLINE + "LEFT JOIN SYS_ORG so on so.ID = su.ORG_ID",
				String.format("%s != null and %s != ''", ENTITY_PERMISSION_TYPE, ENTITY_PERMISSION_TYPE), true);
	}
}
