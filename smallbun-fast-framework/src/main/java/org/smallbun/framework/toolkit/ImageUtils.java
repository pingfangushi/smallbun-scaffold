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
