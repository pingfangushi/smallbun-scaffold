
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
