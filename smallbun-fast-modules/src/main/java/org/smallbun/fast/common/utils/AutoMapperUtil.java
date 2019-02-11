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

package org.smallbun.fast.common.utils;

import org.smallbun.framework.toolkit.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/6 23:13
 */
public class AutoMapperUtil extends org.smallbun.framework.toolkit.AutoMapperUtil {
	/**
	 * 树集合转VO集合
	 *
	 * @param src 需要转化的集合
	 * @param target 转换后的集合
	 * @param targetClass 转换类型
	 * @param <S>
	 * @param <T>
	 * @return
	 */
	public static <S, T> List<T> mappingTreeList(List<S> src, List<T> target, Class<?> targetClass) {
		try {
			target = mappingList(src, target, targetClass);
			for (T vo : target) {
				Field children = ReflectionUtil.getFieldAll(vo, "children");
				children.setAccessible(true);
				if (vo != null && children.get(vo) != null) {
					src = (List<S>) children.get(vo);
					children.set(vo, mappingTreeList(src, new ArrayList<>(), targetClass));
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return target;
	}
}
