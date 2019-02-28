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
public enum SqlMethod {
	
	/**
	 * 查询
	 */
	SELECT_DATA_SCOPE_BY_MAP("selectByMap", "根据columnMap 查询一条数据", "<script>\nSELECT %s FROM %s %s  %s\n</script>"),
	SELECT_DATA_SCOPE_COUNT("selectCount", "查询满足条件总记录数", "<script>\nSELECT COUNT(%s) FROM %s %s\n</script>"),
	SELECT_DATA_SCOPE_LIST("selectList", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s  %s\n</script>"),
	SELECT_DATA_SCOPE_PAGE("selectPage", "查询满足条件所有数据进行过滤（并翻页）", "<script>\nSELECT %s FROM %s  %s  %s\n</script>"),
	SELECT_DATA_SCOPE_MAPS("selectMaps", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s  %s\n</script>"),
	SELECT_DATA_SCOPE_MAPS_PAGE("selectMapsPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s  %s\n</script>"),
	SELECT_DATA_SCOPE_OBJS("selectObjs", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s  %s\n</script>");

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
