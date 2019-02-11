
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

import java.io.*;
import java.util.Calendar;


/**
 * 文件上传类
 * Created by 2689170096@qq.com/SanLi  on 2018/1/8 21:48
 */
public class UploadUtil {

	/**
	 * 上传图片的方法
	 *
	 * @param path
	 * @param inputStream
	 * @param imageName
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(String path, InputStream inputStream, String imageName, String folderPrefix)
			throws IOException {
		int random = (int) (Math.random() * 900) + 100;
		/*设置上传目录*/
		Calendar cal = Calendar.getInstance();
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		month = month.length() == 1 ? "0" + month : month;
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		day = day.length() == 1 ? "0" + day : day;
		String imageUrlPath = folderPrefix + "/" + cal.get(Calendar.YEAR) + "/" + month + "/" + day;
		String imagePath = path + imageUrlPath;
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		/*设置目标文件*/
		String targetImageName = "" + cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND) + cal
				.get(Calendar.MILLISECOND) + random + imageName.substring(imageName.lastIndexOf("."));
		File target = new File(imagePath + "/" + targetImageName);
		/*创建输出流*/
		OutputStream os = new FileOutputStream(target);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = inputStream.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		inputStream.close();
		os.close();
		return "/" + imageUrlPath + "/" + targetImageName;
	}


	/**
	 * 上传图片的方法
	 *
	 * @param imageFile
	 * @param imageName
	 * @return
	 * @throws IOException
	 */
	public static String uploadImage(String path, File imageFile, String imageName) throws IOException {
		/*创建输入流*/
		InputStream is = new FileInputStream(imageFile);
		return uploadImage(path, is, imageName);
	}

	/**
	 * 上传图片的方法
	 *
	 * @param path
	 * @param inputStream
	 * @param imageName
	 * @return
	 * @throws IOException
	 */
	public static String uploadImage(String path, InputStream inputStream, String imageName) throws IOException {
		return uploadFile(path, inputStream, imageName, "images");
	}

}
