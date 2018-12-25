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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * MapUtils
 *
 * @author SanLi
 * Created  by 2689170096@qq.com  on 2018/1/11
 */
public class MapUtils {
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, String> returnMap = new HashMap<>(16);
		Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name;
		String value = "";
		while (entries.hasNext()) {
			entry = entries.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else {
				String[] values = (String[]) valueObj;
				for (String value1 : values) {
					value = value1 + ",";
				}
				value = value.substring(0, value.length() - 1);
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * Bean 转Map
	 * @param o
	 * @return
	 */
	public static Map<String, String> beanToMap(Object o) {
		return JSONObject.parseObject(JSON.toJSONString(o), new TypeReference<Map<String, String>>() {
		});
	}
}
