
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
