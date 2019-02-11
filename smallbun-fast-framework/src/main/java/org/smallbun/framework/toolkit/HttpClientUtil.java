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


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author SanLi
 */
@Slf4j
public class HttpClientUtil {

	private static final int CONN_TIME_OUT = 3000000;
	private static final int READ_TIME_OUT = 3000000;
	private static final String UTF8 = "UTF-8";

	/**
	 * 刷卡接口请求连接
	 *
	 * @param connection
	 * @param jsonString
	 * @param jsoonString2
	 * @return
	 * @throws IOException
	 */
	public String dtoRequestConnectionDto(HttpURLConnection connection, String jsonString, String jsoonString2)
			throws IOException {
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		// 2分钟
		connection.setConnectTimeout(120000);
		connection.setReadTimeout(120000);
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		String content = "param=" + URLEncoder.encode(jsonString, UTF8)
				// 传入加密后的jsonString
				+ "&param1=" + URLEncoder.encode(jsoonString2, UTF8);
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder buffer = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
	}

	/**
	 * 封装HTTP POST方法
	 *
	 * @param
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> paramMap) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = setHttpParams(paramMap);
		UrlEncodedFormEntity param = new UrlEncodedFormEntity(formParams, UTF8);
		httpPost.setEntity(param);
		HttpResponse response = httpClient.execute(httpPost);
		String httpEntityContent = getHttpEntityContent(response);
		httpPost.abort();
		return httpEntityContent;
	}

	public static HttpClientContext httpClientContext;

	static {
		// 创建上下文.用于共享session id
		httpClientContext = HttpClientContext.create();
	}

	public static String postForm(String url, Map<String, String> paramMap, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout) {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		// 用于存放表单数据.
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		// 遍历map 将其中的数据转化为表单数据
		for (Entry<String, String> entry : paramMap.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			// 对表单数据进行url编码
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, UTF8);
			httpPost.setEntity(urlEncodedFormEntity);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			httpPost.setConfig(customReqConf.build());

			HttpResponse response = httpClient.execute(httpPost, httpClientContext);
			// 获取cookie
			CookieStore cookieStore = httpClientContext.getCookieStore();
			return IOUtils.toString(response.getEntity().getContent(), UTF8);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 封装HTTP POST方法
	 *
	 * @param
	 * @param （如JSON串）
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, String data) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "text/json; charset=utf-8");
		httpPost.setEntity(new StringEntity(URLEncoder.encode(data, UTF8)));
		HttpResponse response = httpClient.execute(httpPost);
		String httpEntityContent = getHttpEntityContent(response);
		httpPost.abort();
		return httpEntityContent;
	}

	/**
	 * 封装HTTP GET方法
	 *
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet();
		httpGet.setURI(URI.create(url));
		HttpResponse response = httpClient.execute(httpGet);
		String httpEntityContent = getHttpEntityContent(response);
		httpGet.abort();
		return httpEntityContent;
	}

	/**
	 * 封装HTTP GET方法
	 *
	 * @param
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> paramMap) {
		String httpEntityContent;

		try {
			//获取DefaultHttpClient请求
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet();
			/* 设置超时时间 */
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
					.setConnectionRequestTimeout(1000).setSocketTimeout(60000).build();
			httpGet.setConfig(requestConfig);
			List<NameValuePair> formParams = setHttpParams(paramMap);
			String param = URLEncodedUtils.format(formParams, UTF8);
			httpGet.setURI(URI.create(url + "?" + param));
			HttpResponse response = httpClient.execute(httpGet);
			httpEntityContent = getHttpEntityContent(response);
			httpGet.abort();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return httpEntityContent;
	}

	/**
	 * 封装HTTP PUT方法
	 *
	 * @param
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String put(String url, Map<String, String> paramMap) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut httpPut = new HttpPut(url);
		List<NameValuePair> formParams = setHttpParams(paramMap);
		UrlEncodedFormEntity param = new UrlEncodedFormEntity(formParams, UTF8);
		httpPut.setEntity(param);
		HttpResponse response = httpClient.execute(httpPut);
		String httpEntityContent = getHttpEntityContent(response);
		httpPut.abort();
		return httpEntityContent;
	}

	/**
	 * 封装HTTP DELETE方法
	 *
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String delete(String url) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete httpDelete = new HttpDelete();
		httpDelete.setURI(URI.create(url));
		HttpResponse response = httpClient.execute(httpDelete);
		String httpEntityContent = getHttpEntityContent(response);
		httpDelete.abort();
		return httpEntityContent;
	}

	/**
	 * 封装HTTP DELETE方法
	 *
	 * @param
	 * @param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String delete(String url, Map<String, String> paramMap) throws IOException {
		//获取DefaultHttpClient请求
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete httpDelete = new HttpDelete();
		List<NameValuePair> formParams = setHttpParams(paramMap);
		String param = URLEncodedUtils.format(formParams, UTF8);
		httpDelete.setURI(URI.create(url + "?" + param));
		HttpResponse response = httpClient.execute(httpDelete);
		String httpEntityContent = getHttpEntityContent(response);
		httpDelete.abort();
		return httpEntityContent;
	}

	/**
	 * 设置请求参数
	 *
	 * @param
	 * @return
	 */
	private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
		List<NameValuePair> formParams = new ArrayList<>();
		Set<Entry<String, String>> set = paramMap.entrySet();
		for (Entry<String, String> entry : set) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return formParams;
	}

	/**
	 * 获得响应HTTP实体内容
	 *
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static String getHttpEntityContent(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream is = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF8));
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			return sb.toString();
		}
		return "";
	}

	public static String doGet(String url, Map<String, String> param) {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), UTF8);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * psot 请求
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> param) {
		/* 创建HttpClient对象 */
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString;
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), UTF8);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		} finally {
			try {
				assert response != null;
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}

	/**
	 * @param url  发送的地址
	 * @param json JSON数据
	 * @return
	 */
	public static String doPostJson(String url, String json) {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				assert response != null;
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

	/**
	 * 封装发送数据
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String postRequestByFormEntity(String url, UrlEncodedFormEntity param) {
		String res = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(param);
			log.info("{}", httpPost);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
				HttpEntity reEntity = response.getEntity();
				res = EntityUtils.toString(reEntity, UTF8);
				response.close();
				client.close();
			}
			return res;
		} catch (Exception e) {
			throw new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}

	}

	/**
	 * 返回发送的数据
	 *
	 * @throws UnsupportedEncodingException
	 */
	public static UrlEncodedFormEntity buildPairList(Map<String, String> param) {
		try {
			List<NameValuePair> list = new ArrayList<>();
			for (Entry<String, String> item : param.entrySet()) {
				list.add(new BasicNameValuePair(item.getKey(), item.getValue()));
			}
			log.info("{}", list);
			return new UrlEncodedFormEntity(list, UTF8);
		} catch (Exception e) {
			throw new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	/**
	 * 向客户端返回数据
	 *
	 * @param response
	 * @param obj
	 */
	public static void outPrint(HttpServletResponse response, Object obj) {
		PrintWriter out = null;
		try {
			log.info("<<<<-------outPrint返回数据------->>>>" + obj);
			response.setContentType("text/html;charset=UTF8");
			response.setHeader("progma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			out.print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assert out != null;
			out.flush();
			out.close();
		}
	}
}
