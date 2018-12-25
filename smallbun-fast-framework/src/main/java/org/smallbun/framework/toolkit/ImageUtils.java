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


import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/3/1
 */
public class ImageUtils {

	public static String getUrlImageToBase64(String url) {

		byte[] data = null;
		try {
			if (url == null || "".equals(url.trim())) {
				return null;
			}
			URL u = new URL(url);
			// 打开图片路径
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			// 设置请求方式为GET
			conn.setRequestMethod("GET");
			// 设置超时响应时间为5秒
			conn.setConnectTimeout(5000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			data = new byte[inStream.available()];
			// 读取图片字节数组
			inStream.read(data);
			inStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}
}
