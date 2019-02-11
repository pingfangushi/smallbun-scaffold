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

package org.smallbun.framework.permission.util;

import org.apache.ibatis.mapping.MappedStatement;
import org.smallbun.framework.permission.annotation.DataScopeFilter;

import java.lang.reflect.Method;

/**
 * 自定义权限相关工具类
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2018/11/14 -10:20
 */
public class PermissionUtil {

	/**
	 * 根据 StatementHandler 获取 注解对象
	 * @author GaoYuan
	 * @date 2018/4/17 上午11:45
	 */
	public static DataScopeFilter getPermissionByDelegate(MappedStatement mappedStatement) {
		DataScopeFilter filterPermission = null;
		try {
			String id = mappedStatement.getId();
			String className = id.substring(0, id.lastIndexOf("."));
			String methodName = id.substring(id.lastIndexOf(".") + 1);
			final Class cls = Class.forName(className);
			final Method[] method = cls.getMethods();
			for (Method me : method) {
				if (me.getName().equals(methodName) && me.isAnnotationPresent(DataScopeFilter.class)) {
					filterPermission = me.getAnnotation(DataScopeFilter.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterPermission;
	}
}
