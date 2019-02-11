
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
 * 随机数
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:46
 */
public class RandomUtil {

	/**
	 * 生成六位随机数
	 *
	 * @return
	 */
	public static String random() {

		StringBuffer buffer = new StringBuffer();
		//添加三位随机数
		//生成三个 0-9
		int num1, num2, num3, num4, num5, num6;
		Random rnd = new Random();
		num1 = rnd.nextInt(9);
		num2 = rnd.nextInt(9);
		num3 = rnd.nextInt(9);
		num4 = rnd.nextInt(9);
		num5 = rnd.nextInt(9);
		num6 = rnd.nextInt(9);

		String num = num1 + "" + num2 + "" + num3 + "" + num4 + "" + num5 + "" + num6;
		buffer.append(num);

		return buffer.toString();
	}

	/**
	 * 获得指定长度随机数
	 *
	 * @param length
	 * @return
	 */
	public static String random(int length) {
		String result = "";
		if (length > 0) {
			StringBuffer sb = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < length; i++) {
				int num = rnd.nextInt(9);
				sb.append(num);
			}
			result = sb.toString();
		}
		return result;
	}

	public static int randomSet(int lastNumber, int number) {
		Random random = new Random();
		int num = random.nextInt(number);

		while (num == lastNumber) {
			num = random.nextInt(number);
		}

		return num;
	}

}
