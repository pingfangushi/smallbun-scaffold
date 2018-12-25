
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
