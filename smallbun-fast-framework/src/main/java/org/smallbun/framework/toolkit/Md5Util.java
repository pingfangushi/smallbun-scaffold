
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

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密类
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:44
 */
public class Md5Util {

	/**
	 * 16位加密
	 *
	 * @param plainText plainText
	 * @return String
	 */
	public static String Md516(String plainText) {
		return Md516(plainText, StandardCharsets.UTF_8.name());
	}

	/**
	 * 16位加密
	 *
	 * @param plainText plainText
	 * @return String
	 */
	public static String Md516(String plainText, String charSet) {
		String result = null;
		try {
			byte[] ptBytes = plainText.getBytes(charSet);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(ptBytes);
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			// result = buf.toString(); //md5 32bit
			// result = buf.toString().substring(8, 24))); //md5 16bit
			result = buf.toString().substring(8, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 32位加密
	 *
	 * @param plainText plainText
	 * @return String
	 */
	public static String Md532(String plainText) {
		return Md532(plainText, StandardCharsets.UTF_8.name());
	}

	/**
	 * 32位加密
	 *
	 * @param plainText plainText
	 * @return String
	 */
	public static String Md532(String plainText, String charSet) {
		String result = null;
		try {
			byte[] ptBytes = plainText.getBytes(charSet);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(ptBytes);
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			// result = buf.toString(); //md5 32bit
			// result = buf.toString().substring(8, 24))); //md5 16bit
			result = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj.getBytes("iso-8859-1"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = StringTools.byteToHexString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultString;
	}

	public static String encode(String text) {

		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(text.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				int number = b & 0xff;
				String hex = Integer.toHexString(number);
				if (hex.length() == 1) {
					sb.append("0" + hex);
				} else {
					sb.append(hex);
				}
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
