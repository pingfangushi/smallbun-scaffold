
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

import java.math.BigDecimal;

/**
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:38
 */
public class BigdecimalUtil {

	/**
	 * 比较
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static int compare(BigDecimal first, BigDecimal second) {
		return first.compareTo(second);
	}

	/**
	 * 加法
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static BigDecimal plus(BigDecimal first, BigDecimal second) {
		return first.add(second);
	}

	/**
	 * 减法
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal first, BigDecimal second) {
		return first.subtract(second);
	}

	/**
	 * 乘法
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal first, BigDecimal second) {
		return first.multiply(second);
	}

	/**
	 * 减法
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static BigDecimal divide(BigDecimal first, BigDecimal second) {
		return first.divide(second);
	}

}
