package org.smallbun.framework.injector.enums;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 20:48
 */
public enum SqlMethod {
	/**
	 *  数据过滤查询满足条件所有数据
	 */
	SELECT_DATA_SCOPE_PAGE("selectDataScopePage", "数据过滤查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s\n</script>");

	private final String method;
	private final String desc;
	private final String sql;

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
