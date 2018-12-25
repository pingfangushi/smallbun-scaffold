
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
package org.smallbun.framework.toolkit;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isNotEmpty;

/**
 * 字符串类型处理
 * @author SanLi
 * Created  by 2689170096@qq.com  on 2018/1/26
 */
public class StringUtil {

	public static final String SPLIT_DEFAULT = ",";
	public static final String SPLIT_FILE_DEFAULT = "|";
	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";


	public static String[] splitDefault(final String str) {
		return StringUtils.split(str, SPLIT_DEFAULT);
	}

	/**
	 * 拼接字符串
	 *
	 * @param strs
	 * @return String
	 */
	public static String toAppendStr(Object... strs) {
		StringBuilder sb = new StringBuilder();
		for (Object str : strs) {
			if (isNotEmpty(str)) {
				sb.append(str);
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串列表转成字符串
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append("'" + list.get(i) + "',");
				} else {
					sb.append("'" + list.get(i) + "'");
				}
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * 获得字符串长度
	 * @param s
	 * @return
	 */
	public static int getStringLength(String s) {
		int length = 0;
		if ((null != s) && (!"".equals(s))) {
			for (int i = 0; i < s.length(); i++) {
				int ascii = Character.codePointAt(s, i);
				if (ascii >= 0 && ascii <= 255) {
					length++;
				} else {
					length += 2;
				}
			}
		}
		return length;
	}

}
