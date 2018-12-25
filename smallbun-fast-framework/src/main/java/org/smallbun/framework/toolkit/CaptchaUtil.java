
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


import java.util.Random;

/**
 * 随机生成验证码类
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:38
 */
public class CaptchaUtil {

	/**
	 * 生成随机数
	 *
	 * @param length        int 长度
	 * @param isAllowRepeat 是否允许重复 true：允许，false：不允许 （注：需要的长度必须小于基数值：10，才生效）
	 * @return
	 */
	public static String generateRandom(int length, boolean isAllowRepeat) {
		StringBuilder result = new StringBuilder();
		int radix = 10;
		while (result.length() < length) {
			Random random = new Random();
			String number = String.valueOf(random.nextInt(radix));
			if (!isAllowRepeat && (radix >= length)) {
				if (result.toString().contains(number)) {
				} else {
					result.append(number);
				}
			} else {
				result.append(number);
			}
		}
		return result.toString();
	}

	/**
	 * 获取不重复的编号
	 *
	 * @return
	 */
	public static String getMillisecondNotRepcetStr() {
		return DateUtil.getMillisecondStr() + generateRandom(6, false);
	}

	/**
	 * 生成验证码
	 *
	 * @return
	 */
	public static String generateCaptcha() {
		String result = "";
		//获取4个不重复的数字
		String firstCode = generateRandom(4, false);
		String secondCode = generateRandom(1, true);
		String thirdCode = generateRandom(1, true);
		result = insertRandomStr(firstCode, secondCode);
		result = insertRandomStr(result, thirdCode);
		return result;
	}

	/**
	 * 随机查询字符串
	 *
	 * @param sourceStr
	 * @param insertStr
	 * @return
	 */
	public static String insertRandomStr(String sourceStr, String insertStr) {
		Random random = new Random();
		int number = random.nextInt(sourceStr.length());
		String startStr = sourceStr.substring(0, number);
		String endStr = sourceStr.substring(number);
		return startStr + insertStr + endStr;
	}
}
