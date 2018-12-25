
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

import java.util.UUID;

/**
 * UUID生成
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:23
 */
public class UUIDUtil {

	/**
	 * 获取UUID
	 *
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 获取UUID
	 *
	 * @return
	 */
	public static String[] getUUID(int number) {
		String[] result = null;
		if (number > 0) {
			result = new String[number];
			for (int i = 0; i < number; i++) {
				result[i] = getUUID();
			}
		}
		return result;
	}

}
