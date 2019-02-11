
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
