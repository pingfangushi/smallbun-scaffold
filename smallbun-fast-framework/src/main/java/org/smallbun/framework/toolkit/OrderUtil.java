
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


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单util
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:45
 */
public class OrderUtil {

	private static final AtomicInteger WORK_COUNT = new AtomicInteger(1);


	/**
	 * 获得数字的位数
	 *
	 * @param num
	 * @return
	 */
	private static int calBitCount(int num) {
		return getBitCount(num, 1);
	}

	/**
	 * 获取位数
	 *
	 * @param num
	 * @param count
	 * @return
	 */
	private static int getBitCount(int num, int count) {
		int plus = num / 10;
		if (plus != 0) {
			count++;
			count = getBitCount(plus, count);
		}
		return count;
	}


	/**
	 * 获得workid
	 *
	 * @return
	 */
	private static String getWorkId() {
		int count = WORK_COUNT.getAndAdd(1);
		if (count >= 999999) {
			WORK_COUNT.set(1);
		}
		//随机数补位数
		int length = calBitCount(count);
		String rndStr = RandomUtil.random(12 - length);
		String assembleStr = count + rndStr;
		return assembleStr;
	}

	/**
	 * 获得订单号
	 *
	 * @return
	 */
	public static String getOrderNo() {
		return DateUtil.getNowtimeStr() + getWorkId();
	}


}
