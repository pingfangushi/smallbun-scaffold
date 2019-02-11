
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证码
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:46
 */
public class RegexValidateUtil {

	private static final Pattern PATTERN = Pattern.compile("[0-9]+");

	/**
	 * 验证是否是邮箱
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		if (email.contains("@")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 检测是否为手机
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		boolean flag = false;
		String check = "^1\\d{10}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(mobile);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 验证字符是否是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean flag = false;
		if (null != str) {
			Matcher match = PATTERN.matcher(str);
			if (match.matches()) {
				flag = true;
			}
		}
		return flag;
	}


}
