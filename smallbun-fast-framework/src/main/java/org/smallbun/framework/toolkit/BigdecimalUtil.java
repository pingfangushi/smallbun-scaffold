
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
