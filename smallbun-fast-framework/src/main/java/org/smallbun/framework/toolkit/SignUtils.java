

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


import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * 签名工具
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:17
 */

public class SignUtils {
	private static Logger log = LoggerFactory.getLogger(SignUtils.class);

	/**
	 * ascii码升序,MD5消息摘要
	 * 从map集合转换为ascii码升序集合
	 *
	 * @param map    请求
	 * @param md5Key 签名密钥
	 * @return
	 */
	public static String getSignFromMap(Map<String, String> map, String md5Key) {
		Map<String, String> sortMap = new TreeMap<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isNotBlank(entry.getValue())) {
				sortMap.put(entry.getKey(), entry.getValue());
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> keyValue : sortMap.entrySet()) {
			sb.append(keyValue.getKey()).append("=").append(keyValue.getValue()).append("&");
		}
		sb.append("key=").append(md5Key);
		log.info(sb.toString());
		String result = sb.toString();
		result = DigestUtils.md5Hex(result).toUpperCase();
		return result;
	}

	/**
	 * ascii码升序,MD5消息摘要
	 * json字符串转换为ascii码升序集合并拼接待签名数据  repository
	 *
	 * @param json
	 * @param md5Key
	 * @return
	 */
	public static String getSignFromJson(JSONObject json, String md5Key) {
		//签名
		ObjectMapper mapper = new ObjectMapper();
		//返回数据转换成待签名数据
		TreeMap<String, String> respMap = null;
		try {
			respMap = mapper.readValue(json.toString(), new TypeReference<TreeMap<String, String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		//拼接待签名字符串
		assert respMap != null;
		for (Map.Entry<String, String> entry : respMap.entrySet()) {
			if (StringUtils.isNotBlank(entry.getValue())) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		sb.append("key=" + md5Key);
		return DigestUtils.md5Hex(sb.toString()).toUpperCase();
	}

	/**
	 * 验签
	 * json字符串转换为ascii码升序集合并拼接待签名数据
	 *
	 * @param signJson 待签名json对象
	 * @param md5Key   签名密钥,MD5
	 * @return 验签结果, true, false
	 */
	public static boolean checkSign(JSONObject signJson, String md5Key) {
		String signData = (String) signJson.get("signData");
		//sign置空
		signJson.put("signData", "");
		String signForNow = getSignFromJson(signJson, md5Key);
		log.info("前端传递的签名->:{},后端加密后的签名->:{}", signData, signForNow);
		return signData.equals(signForNow);
	}

	/**
	 * 微信公众号验签
	 * json字符串转换为ascii码升序集合并拼接待签名数据
	 *
	 * @param map    待签名resp对象
	 * @param md5Key 签名密钥,MD5
	 * @return 验签结果, true, false
	 */
	public static boolean checkWechatJsApiSign(Map<String, String> map, String md5Key) {
		String signData = map.get("signData");
		//sign置空
		map.put("signData", "");
		String signForNow = getSignFromMap(map, md5Key);
		log.info("前端传递的签名->:{},后端加密后的签名->:{}", signData, signForNow);
		return signData.equals(signForNow);
	}

	/**
	 * 获取需要传递的URL,用户注册支付共用
	 *
	 * @param o
	 * @param signKey 加密串
	 * @return 对象
	 * @throws Exception
	 */
	public static String getEncryption(Object o, String signKey) {
		o = getEncryptionPackage(o, signKey);
		StringBuffer buffer = new StringBuffer();
		Field[] fields = o.getClass().getDeclaredFields();
		String attributeName;
		for (int i = 0; i < fields.length; i++) {
			attributeName = fields[i].getName();
			Object invoke = null;
			try {
				invoke = o.getClass()
						.getMethod("get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1))
						.invoke(o);
			} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				e.printStackTrace();
			}
			if (invoke == null) {
				invoke = "";
			}
			if (i == 0) {
				buffer.append("?").append(attributeName).append("=").append(invoke);
			} else {
				buffer.append("&").append(attributeName).append("=").append(invoke);
			}
		}
		return buffer.toString();
	}

	/**
	 * 对参数进行加密,并封装到对象中返回
	 *
	 * @param o
	 * @return 加密封装后的对象
	 * @throws Exception
	 * @author SanLi
	 */
	public static Object getEncryptionPackage(Object o, String signKey) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			StringBuilder buffer = new StringBuilder();
			int lenth = fields.length;
			String attributeName;
			for (int i = 0; i < lenth - 1; i++) {
				attributeName = fields[i].getName();
				Object invoke = o.getClass()
						.getMethod("get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1))
						.invoke(o);
				if (invoke == null) {
					invoke = "";
					o.getClass()
							.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1),
									String.class).invoke(o, "");
				}
				buffer.append(invoke).append("|");
			}
			buffer.append(signKey);
			//加密后的字符串
			String encode = Md5Util.encode(buffer.toString());
			o.getClass().getMethod("setSignature", String.class).invoke(o, encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @param object
	 * @return
	 */
	public static Map<String, Object> objectMap(Object object) {
		com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) JSON.toJSON(object);
		Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
		Map<String, Object> map = new HashMap<>(16);
		for (Map.Entry<String, Object> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public static Object convertBean2Bean(Object from, Object to) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
			PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor p : ps) {
				Method getMethod = p.getReadMethod();
				Method setMethod = p.getWriteMethod();
				if (getMethod != null && setMethod != null) {
					try {
						Object result = getMethod.invoke(from);
						setMethod.invoke(to, result);
					} catch (Exception e) {
						// 如果from没有此属性的get方法，跳过
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	/**
	 * 验证签名是否正确
	 *
	 * @param o   验签的对象
	 * @param key 加密串
	 * @return
	 */
	public static Boolean checkSignature(Object o, String key) {
		try {
			String signatureFront = (String) o.getClass().getMethod("getSignature").invoke(o);
			String signatureAfter = (String) getEncryptionPackage(o, key).getClass().getMethod("getSignature")
					.invoke(o);
			log.info("传递来的签名:{},验证后的签名:{}", signatureFront, signatureAfter);
			if ((signatureFront).equals(signatureAfter)) {
				log.info("签名验证成功!");
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException("签名验证错误");
		}
	}
}
