package org.smallbun.framework.toolkit;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 拼接参数
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/1 21:48
 */
public class SpliceParameterUtil {
	/**
	 * 拼接get 请求参数
	 * @param url
	 * @param params
	 * @return
	 */
	public static String urlBuilder(String url, List<NameValuePair> params) {
		return url + "?" + URLEncodedUtils.format(params, "UTF-8");
	}


	/**
	 * 拼接Post请求参数
	 * @param params
	 * @return
	 */
	public static String paramsToString(Map<String, String> params) {
		if (params != null && params.size() > 0) {
			String paramsEncoding = "UTF-8";
			StringBuilder encodedParams = new StringBuilder();
			try {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
					encodedParams.append('=');
					encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
					encodedParams.append('&');
				}
				return encodedParams.toString();
			} catch (UnsupportedEncodingException uee) {
				throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
			}
		}
		return "";
	}
}
