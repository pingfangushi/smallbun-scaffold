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


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/16 17:40
 */
public class ReflectionHelper {
	/**
	 * 获取指定类型内的所有方法
	 *
	 * @param clazz 类型
	 * @return
	 */
	public static Method[] getMethods(Class<?> clazz) {
		return clazz.getDeclaredMethods();
	}

	/**
	 * 递归获取指定类型内以及类型的所有上级内定义的方法
	 *
	 * @param clazz 类型
	 * @return
	 */
	public static List<Method> getAllMethods(Class<?> clazz) {
		//自动注册继承的接口
		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces != null && interfaces.length > 0) {
			for (Class<?> anInterface : interfaces) {
				getAllMethods(anInterface);
			}
		}
		return null;
	}

	/**
	 * 获取实体类内 & 父类内的所有字段
	 *
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> result = new LinkedList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			result.add(field);
		}
		Class<?> superClass = clazz.getSuperclass();
		if (superClass.equals(Object.class)) {
			return result;
		}
		result.addAll(getAllFields(superClass));
		return result;
	}

	/**
	 * 获取实体类内的所有字段并自动排除存在@Transient注解的字段
	 *
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllFieldsExcludeTransient(Class<?> clazz) {
		List<Field> result = new LinkedList<Field>();
		List<Field> list = getAllFields(clazz);
		for (Field field : list) {
			if (Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			result.add(field);
		}
		return result;
	}

	/**
	 * 获取字段
	 * 检索本类内是否存在，检索不到再去找父类内的字段
	 *
	 * @param clazz
	 * @return
	 */
	public static Field getField(Class<?> clazz, String fieldName) throws Exception {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			try {
				field = clazz.getSuperclass().getDeclaredField(fieldName);
			} catch (NoSuchFieldException e1) {
				try {
					field = clazz.getSuperclass().getSuperclass().getDeclaredField(fieldName);
				} catch (NoSuchFieldException e2) {

				}
			}
		}
		if (field == null) {
			throw new RuntimeException(
					"Not Found Field：" + fieldName + " in Class " + clazz.getName() + " and super Class.");
		}
		return field;
	}

}
