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

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

/**
 * 网络请求工具类
 *
 * @author SanLi
 * Created  by 2689170096@qq.com  on 2018/1/15
 */
public class HttpUtil {
	/**
	 * 日志
	 **/
	public static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 提交Http请求 POST
	 *
	 * @param url     地址
	 * @param content 内容
	 * @return
	 */
	public static String httpPost(String url, String content) {
		String result = null;
		HttpPost request = null;
		CloseableHttpResponse response = null;
		try {
			StringBuffer buffer = new StringBuffer();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			request = new HttpPost(url);
			StringEntity entity = new StringEntity(content, StandardCharsets.UTF_8.name());
			entity.setContentType("application/json;charset=UTF-8");
			request.setEntity(entity);

			//设置请求超时时间和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000)
					.setConnectionRequestTimeout(5000).build();
			request.setConfig(requestConfig);

			HttpContext context = new BasicHttpContext();
			response = httpClient.execute(request, context);

			if ((null != response) && (null != response.getStatusLine()) && (response.getStatusLine().getStatusCode()
					== HttpStatus.SC_OK)) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8.name()));
				String output = null;
				while ((output = br.readLine()) != null) {
					buffer.append(output);
				}
				result = buffer.toString();
			} else {
				logger.warn("响应错误，状态码：" + response.getStatusLine().getStatusCode() + " URL：" + url);
			}
		} catch (MalformedURLException ex) {
			logger.error("响应错误，URL：" + url, ex);
		} catch (IOException ex) {
			logger.error("IO错误，URL：" + url, ex);
		} catch (Exception ex) {
			logger.error("请求错误，URL：" + url, ex);
		} finally {
			if (null != request) {
				request.releaseConnection();
				;
			}
			if (null != response) {
				try {
					response.close();
				} catch (IOException ex) {
					logger.error("关闭链接错误", ex);
				}
			}
		}
		return result;
	}

	/**
	 * 提交Http请求 Get
	 *
	 * @param url 地址
	 * @return
	 */
	public static String httpGet(String url) {
		String result = null;
		HttpGet request = null;
		CloseableHttpResponse response = null;
		try {
			StringBuffer buffer = new StringBuffer();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			request = new HttpGet(url);


			//设置请求超时时间和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000)
					.setConnectionRequestTimeout(5000).build();
			request.setConfig(requestConfig);

			HttpContext context = new BasicHttpContext();
			response = httpClient.execute(request, context);

			if ((null != response) && (null != response.getStatusLine()) && (response.getStatusLine().getStatusCode()
					== HttpStatus.SC_OK)) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8.name()));
				String output = null;
				while ((output = br.readLine()) != null) {
					buffer.append(output);
				}
				result = buffer.toString();
			} else {
				logger.warn("响应错误，状态码：" + response.getStatusLine().getStatusCode() + " URL：" + url);
			}
		} catch (MalformedURLException ex) {
			logger.error("响应错误，URL：" + url, ex);
		} catch (IOException ex) {
			logger.error("IO错误，URL：" + url, ex);
		} catch (Exception ex) {
			logger.error("请求错误，URL：" + url, ex);
		} finally {
			if (null != request) {
				request.releaseConnection();
				;
			}
			if (null != response) {
				try {
					response.close();
				} catch (IOException ex) {
					logger.error("关闭链接错误", ex);
				}
			}
		}
		return result;
	}
}
