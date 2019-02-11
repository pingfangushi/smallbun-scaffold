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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:14
 */
public class EncodeUtil {
	private static Logger log = LoggerFactory.getLogger(EncodeUtil.class);

	/**
	 * HEX编码
	 *
	 * @param dataByteAry 待编码数据-byte数组
	 * @return
	 */
	public static char[] enByHEX(byte[] dataByteAry) {
		return Hex.encodeHex(dataByteAry, false);
	}

	/**
	 * HEX解码
	 *
	 * @param dataCharAry 待解码数据-char数组
	 * @return
	 */
	public static byte[] deByHEX(char[] dataCharAry) {
		try {
			return Hex.decodeHex(dataCharAry);
		} catch (DecoderException e) {
			log.error("HEX解码发生异常", e);
			return null;
		}
	}

	/**
	 * BASE64编码（数组->数组）
	 *
	 * @param dataByteAry 待编码数据-byte数组
	 * @return 数组
	 */
	public static byte[] enByBASE64(byte[] dataByteAry) {
		return Base64.encodeBase64(dataByteAry);
	}

	/**
	 * BASE64解码（数组->数组）
	 *
	 * @param dataByteAry 待解码数据-byte数组
	 * @return
	 */
	public static byte[] deByBASE64(byte[] dataByteAry) {
		return Base64.decodeBase64(dataByteAry);
	}

	/**
	 * byte字节数组 -> 二进制字符串 ,eg:([96, 0, 4, 0, 0] -> 000100011001...) 注：转换位图时使用
	 *
	 * @param byteAry
	 * @return
	 * @author jhjiang
	 */
	public static String byteAryToBinary(byte[] byteAry) {
		StringBuffer sbf = new StringBuffer(byteAry.length * Byte.SIZE);
		for (int i = 0; i < Byte.SIZE * byteAry.length; i++) {
			sbf.append((byteAry[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		}
		return sbf.toString();
	}

	/**
	 * 二进制字符串 -> 字节数组
	 *
	 * @param binaryStr
	 * @return
	 * @author jhjiang
	 */
	public static byte[] binaryToByteAry(String binaryStr) {
		int byteSize = (binaryStr.length() + Byte.SIZE - 1) / Byte.SIZE;
		int strLen = byteSize * Byte.SIZE;
		binaryStr = String.format("%" + strLen + "s", binaryStr).replace(' ', '0');
		byte[] byteAry = new byte[byteSize];
		char c;
		for (int i = 0; i < strLen; i++) {
			if ((c = binaryStr.charAt(i)) == '1') {
				byteAry[i / Byte.SIZE] = (byte) (byteAry[i / Byte.SIZE] | 0x80 >>> (i % Byte.SIZE));
			} else if (c != '0') {
				log.error("传入的二进制字符串必须为0或1，第" + i + "位=" + c);
				return null;
			}
		}
		return byteAry;
	}

	/**
	 * 字节数组转换为十六进制-（数组->字符串）
	 *
	 * @param byteAry
	 * @return
	 */
	public static String byteAryToHex(byte[] byteAry) {
		return new String(enByHEX(byteAry));
	}

	/**
	 * 十六进制转换为字节数组-（字符串->数组）
	 *
	 * @param hex
	 * @return
	 */
	public static byte[] hexToByteAry(String hex) {
		return deByHEX(hex.toCharArray());
	}

	/**
	 * 十六进制转换为字节数组（指定转换后的长度）
	 *
	 * @param hex         待转换数据
	 * @param byteArySize 转换后的字节长度，前面补0
	 * @return
	 */
	public static byte[] hexToByteAry(String hex, int byteArySize) {
		if (byteArySize <= 0) {
			return new byte[0];
		}
		hex = String.format("%" + byteArySize * 2 + "s", hex).replace(' ', '0');
		return hexToByteAry(hex);
	}
}
