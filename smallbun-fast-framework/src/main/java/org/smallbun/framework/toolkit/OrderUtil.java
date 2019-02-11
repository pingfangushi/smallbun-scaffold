
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
