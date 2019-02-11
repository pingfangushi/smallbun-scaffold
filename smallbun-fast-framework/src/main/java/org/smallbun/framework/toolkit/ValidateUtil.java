
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


import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共验证
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:50
 */
public class ValidateUtil {
	private static Pattern pattern = Pattern.compile(
			"((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
	private static Pattern patternUrl = Pattern.compile("((^http)|(^https))://(w)+.(w)+");

	/**
	 * 正则验证
	 *
	 * @param regex
	 * @param str
	 * @return
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 验证邮箱是否正确
	 *
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		boolean flag = false;
		if ((null != email) && (!"".equals(email)) && (email.contains("@"))) {
			String[] emailArray = email.split("@");
			if (emailArray.length == 2) {
				int before = StringUtil.getStringLength(emailArray[0]);
				int end = StringUtil.getStringLength(emailArray[1]);
				if (before > 0 && before < 200 && end > 0 && end < 200) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 验证是否含有非法字符
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateStandStr(String str) {
		String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
		return match(regex, str);
	}

	/**
	 * 验证手机号
	 *
	 * @param str
	 * @return
	 */
	public static boolean validatePhoneStr(String str) {
		String regex = "^1(3[0-9]|4[57]|5[0-9]|7[0-9]|8[0-9])\\d{8}$";
		return match(regex, str);
	}

	/**
	 * 验证密码
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateMd5Pwd(String str) {
		boolean flag = false;
		if ((null != str) && (validateStandStr(str)) && (
				StringUtil.getStringLength(str) == 32)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 验证密码
	 *
	 * @param str
	 * @return
	 */
	public static boolean validatePwd(String str) {
		boolean flag = false;
		if (StringUtil.getStringLength(str) >= 6
				&& StringUtil.getStringLength(str) <= 12) {
			flag = true;
		}
		if (flag) {
			String regex = "^[a-zA-Z0-9]+$";
			return match(regex, str);
		}
		return flag;
	}


	/**
	 * 验证验证码
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateCaptcha(String str) {
		boolean flag = false;
		if ((null != str) && (RegexValidateUtil.isNumber(str))
				&& StringUtil.getStringLength(str) == 6) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 验证字符串
	 *
	 * @param userName
	 * @return
	 */
	public static boolean validateUserName(String userName) {
		boolean flag = validateStandStr(userName);
		if (flag) {
			int count = StringUtil.getStringLength(userName);
			flag = count >= 6 && count <= 20;
		}
		return flag;
	}

	/**
	 * 保密字符串转换
	 *
	 * @param no
	 * @return
	 */
	public static String convertSecretno(String no, int length) {
		String result = null;
		if ((null != no) && (no.length() > length)) {
			String begin = no.substring(0, no.length() - length - 1);
			String end = no.substring(no.length() - 1);
			StringBuilder convert = new StringBuilder();
			for (int i = 0; i < length; i++) {
				convert.append("*");
			}
			result = begin + convert + end;
		}
		return result;
	}

	/**
	 * 手机号码 正则判断
	 *
	 * @param str
	 * @return
	 */
	public static boolean isMobileLegal(String str) {
		String regExp = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}


	/**
	 * 验证验证码
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateTelNumber(String str) {
		boolean flag = false;
		if (null != str) {
			Matcher match = pattern.matcher(str);
			if (match.matches()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 验证网址
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateUrl(String str) {
		boolean flag = false;
		if ((null != str)) {
			Matcher match = patternUrl.matcher(str);
			if (match.matches()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 验证图片类型
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean validateImgFileType(String fileName) {
		boolean flag = false;
		if ((null != fileName) && (!"".equals(fileName))) {
			int index = fileName.lastIndexOf(".");
			if (index > 0) {
				String type = fileName.toLowerCase().substring(index + 1);
				if ("jpg".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type) || "gif".equalsIgnoreCase(type)
						|| "jpeg".equalsIgnoreCase(type)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 验证swf文件类型
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean validateSwfFileType(String fileName) {
		boolean flag = false;
		if ((null != fileName) && (!"".equals(fileName))) {
			int index = fileName.lastIndexOf(".");
			if (index > 0) {
				String type = fileName.substring(index + 1);
				if ("swf".equalsIgnoreCase(type)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 验证flv文件类型
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean validateVideoFileType(String fileName) {
		boolean flag = false;
		if ((null != fileName) && (!"".equals(fileName))) {
			int index = fileName.lastIndexOf(".");
			if (index > 0) {
				String type = fileName.substring(index + 1);
				if ("mp4".equalsIgnoreCase(type) || "mpeg".equalsIgnoreCase(type) || "mov".equalsIgnoreCase(type)
						|| "flv".equalsIgnoreCase(type) || "avi".equalsIgnoreCase(type)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	private static Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

	/**
	 * 验证正整数
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateInteger(String str) {
		boolean flag = false;

		if ((null != str)) {
			Pattern pattern = NUMBER_PATTERN;
			Matcher match = pattern.matcher(str);
			if (match.matches()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 删除文件
	 *
	 * @param sPath
	 * @return
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 验证ip地址
	 *
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIp(String ipAddress) {
		String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
				+ "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	public static String getFileSize(long fileSize) {
		String fileSizeStr = "";
		DecimalFormat df = new DecimalFormat("#.0");
		if (fileSize < 1024) {
			fileSizeStr = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeStr = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeStr = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeStr = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeStr;
	}

	/**
	 * 配置是否是数字和字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateNumberAndLetter(String str) {
		String regex = "[A-Za-z0-9]+";
		return match(regex, str);
	}

	/**
	 * 配置是否是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateNumber(String str) {
		String regex = "[0-9]+";
		return match(regex, str);
	}

	/**
	 * 配置是否是大写字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateLargeLetter(String str) {
		String regex = "[A-Z]+";
		return match(regex, str);
	}

	/**
	 * 配置是否字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateLetter(String str) {
		String regex = "[A-Za-z]+";
		return match(regex, str);
	}

	/**
	 * 验证毫秒数  mm:ss:SSS
	 *
	 * @param str
	 * @return
	 */
	public static boolean validateMillisecond(String str) {
		boolean flag = false;
		if (null != str && !"".equals(str)) {
			String[] timeArray = str.split(":");
			if (timeArray.length == 3) {
				boolean minFlag = false, secFlag = false, microFlag = false;
				//分钟
				if (null != timeArray[0] && !"".equals(timeArray[0]) && timeArray[0].length() == 2 && validateNumber(
						timeArray[0])) {
					minFlag = true;
				}
				//秒
				if (null != timeArray[1] && !"".equals(timeArray[1]) && timeArray[1].length() == 2 && validateNumber(
						timeArray[1])) {
					secFlag = true;
				}
				//毫秒
				if (null != timeArray[2] && !"".equals(timeArray[2]) && timeArray[2].length() == 3 && validateNumber(
						timeArray[2])) {
					microFlag = true;
				}
				if (minFlag && secFlag && microFlag) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 经度正则验证
	 *
	 * @param longitude
	 * @return
	 */
	public static boolean isLongitude(float longitude) {
		boolean flag;
		String regex = "^(-?((180)|(((1[0-7]\\d)|(\\d{1,2}))(\\.\\d+)?)))$";
		String longitudeStr = String.valueOf(longitude);
		flag = longitudeStr.matches(regex);
		return flag;
	}

	/**
	 * 纬度正则验证
	 *
	 * @param latitude
	 * @return
	 */
	public static boolean isLatitude(float latitude) {
		boolean flag;
		String regex = "^(-?((90)|((([0-8]\\d)|(\\d{1}))(\\.\\d+)?)))$";
		String latitudeStr = String.valueOf(latitude);
		flag = latitudeStr.matches(regex);
		return flag;
	}

}
