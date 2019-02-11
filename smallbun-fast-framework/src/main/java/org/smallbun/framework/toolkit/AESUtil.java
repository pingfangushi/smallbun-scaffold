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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Random;


/**
 * AES加解密工具类
 *
 * @author SanLi
 * @create 2017/11/27-14:42
 * @简书：http://www.jianshu.com/u/d5950a6af4cd
 * @博客: http://www.leshalv.net
 **/
public class AESUtil {
	private static Logger log = LoggerFactory.getLogger(AESUtil.class);

	public static String encryptAES(String content, String key) {
		try {
			return bytesToHexString(encryptAES(key.getBytes(), content.getBytes()));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * AES加密
	 *
	 * @param key     密钥信息
	 * @param content 待加密信息
	 */
	public static byte[] encryptAES(byte[] key, byte[] content) throws Exception {
		// 不是16的倍数的，补足
		int base = 16;
		key = getBytes(key, base);
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] tgtBytes = cipher.doFinal(content);
		return tgtBytes;
	}

	public static String decryptAES(String content, String key) {
		try {
			byte[] contentByte = hexStringToByte(content);
			return new String(decryptAES(key.getBytes(), contentByte), "UTF-8");
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * AES解密
	 *
	 * @param key     密钥信息
	 * @param content 待加密信息
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptAES(byte[] key, byte[] content) throws Exception {
		// 不是16的倍数的，补足
		int base = 16;
		key = getBytes(key, base);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] tgtBytes = cipher.doFinal(content);
		return tgtBytes;
	}

	private static byte[] getBytes(byte[] key, int base) {
		if (key.length % base != 0) {
			int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(key, 0, temp, 0, key.length);
			key = temp;
		}
		return key;
	}

	/**
	 * 生成16位随机密钥
	 *
	 * @return 16位随机秘钥
	 */
	public static String randomAESKey() {
		StringBuffer key = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			key.append(makeOneKey());
		}
		return key.toString();
	}

	private static String makeOneKey() {
		Random random = new Random();
		int randomInt = random.nextInt(16);
		if (randomInt > -1 && randomInt < 10) {
			return String.valueOf(randomInt);
		} else {
			switch (randomInt) {
				case 10:
					return "A";
				case 11:
					return "B";
				case 12:
					return "C";
				case 13:
					return "D";
				case 14:
					return "E";
				case 15:
					return "F";
				default:
					return "0";
			}
		}
	}

	/**
	 * 把字节数组转换成16进制字符串
	 *
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 把16进制字符串转换成字节数组
	 *
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] aChar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(aChar[pos]) << 4 | toByte(aChar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
}
