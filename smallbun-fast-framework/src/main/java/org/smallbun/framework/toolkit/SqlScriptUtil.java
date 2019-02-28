package org.smallbun.framework.toolkit;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.*;

/**
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-02-28 09:44
 */
public class SqlScriptUtil {
	/**
	 * <p>
	 * 获取 带 when 标签的脚本
	 * </p>
	 *
	 * @param sqlScript sql 脚本片段
	 * @param ifTest 条件
	 * @param newLine 是否换行
	 * @return if 脚本
	 */
	public static String convertWhen(final String sqlScript, final String ifTest, boolean newLine) {
		String newSqlScript = sqlScript;
		if (newLine) {
			newSqlScript = NEWLINE + newSqlScript + NEWLINE;
		}
		return String.format(NEWLINE.concat("<when test=\"%s" + QUOTE + RIGHT_CHEV + "%s" + "</when>"), ifTest,
				newSqlScript);
	}
}
