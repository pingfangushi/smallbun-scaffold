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


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @author SanLi
 * @create 2017/12/1-15:39
 * @简书：http://www.jianshu.com/u/d5950a6af4cd
 * @博客: http://www.leshalv.net
 **/

public class SecretUtils {


	/**
	 * 定义加密算法，有DES、DESede(即3DES)、Blowfish
	 */
	private static final String Algorithm = "DESede";

	/**
	 * 加密方法
	 *
	 * @param src 源数据的字节数组
	 * @return
	 */
	public static byte[] encryptMode(byte[] src, String key) {
		try {
			// 生成密钥
			SecretKey desKey = new SecretKeySpec(build3DesKey(key), Algorithm);
			// 实例化负责加密/解密的Cipher工具类
			Cipher c1 = Cipher.getInstance(Algorithm);
			// 初始化为加密模式
			c1.init(Cipher.ENCRYPT_MODE, desKey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 *
	 * @param src 密文的字节数组
	 * @return
	 */
	public static byte[] decryptMode(byte[] src, String key) {
		try {
			SecretKey desKey = new SecretKeySpec(build3DesKey(key), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			// 初始化为解密模式
			c1.init(Cipher.DECRYPT_MODE, desKey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字符串生成密钥字节数组
	 *
	 * @param keyStr 密钥字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
		// 声明一个24位的字节数组，默认里面都是0

		byte[] key = new byte[24];
		// 将字符串转成字节数组
		byte[] temp = keyStr.getBytes("UTF-8");

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
}
