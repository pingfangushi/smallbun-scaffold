/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.smallbun.framework.injector.enmus;

/**
 * MybatisPlus 支持 SQL 方法
 *
 * @author hubin
 * @since 2016-01-23
 */
public enum SqlMethod {/**
 *
 */
SELECT_DATA_SCOPE_PAGE("selectPage", "查询满足条件所有数据进行过滤（并翻页）", "<script>\nSELECT %s FROM %s "
				+ "\n<when test=\"1=1\">LEFT JOIN SYS_USER su on a.CREATOR=su.id  </when> %s\n</script>");
	/**
	 *
	 */
	private final String method;
	/**
	 *
	 */
	private final String desc;
	/**
	 *
	 */
	private final String sql;

	/**
	 *
	 * @param method
	 * @param desc
	 * @param sql
	 */
	SqlMethod(String method, String desc, String sql) {
		this.method = method;
		this.desc = desc;
		this.sql = sql;
	}

	public String getMethod() {
		return method;
	}

	public String getDesc() {
		return desc;
	}

	public String getSql() {
		return sql;
	}

}
