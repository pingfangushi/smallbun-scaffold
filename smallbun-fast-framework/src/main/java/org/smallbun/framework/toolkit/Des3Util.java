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


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

/**
 * 3DES加密工具类
 *
 * @author SanLi
 * Created  by 2689170096@qq.com  on 2018/1/15
 */
public class Des3Util {
	/**
	 * 密钥 长度不得小于24
	 */
	public final static String DES_KEY = "Klu&pVSlHSD@2M#p6NRRjv0Gz#nkGAMC#fUfxdpWuKnb3oXG%%zb^tywMN97bU2HxqHy&50dMQSOgP#mNvNIvfOg0z8u!BY2ZeL";
	/**
	 * 向量 可有可无 终端后台也要约定
	 */
	private final static String iv = "01234567";

	/**
	 * 3DES加密
	 *
	 * @param plainText 普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, String key, String encoding) {
		try {
			Key desKey;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
			desKey = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return Base64.getUrlEncoder().encodeToString(encryptData);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 3DES解密
	 *
	 * @param encryptText 加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText, String key, String encoding) {
		try {
			Key desKey;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
			desKey = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
			byte[] decryptData = cipher.doFinal(Base64.getUrlDecoder().decode(encryptText));
			return new String(decryptData, encoding);
		} catch (Exception e) {
			throw new RuntimeException("3DES 解密异常");
		}
	}
}
