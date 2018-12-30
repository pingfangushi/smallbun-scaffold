package org.smallbun.framework.toolkit;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 自动
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/30 13:59
 */
public class AutoMapperUtil {
	public static <TSource, TDestination> TDestination mapping(TSource source, TDestination destination) {
		Method[] srcMethods = source.getClass().getMethods();
		Method[] destMethods = destination.getClass().getMethods();
		for (Method m : srcMethods) {
			String srcMethodName = m.getName();
			//调用get方法
			if (srcMethodName.startsWith("get")) {
				try {
					//获取当前方法返回值(获取当前属性值)
					Object getValue = m.invoke(source);
					for (Method dm : destMethods) {
						//目标方法名称
						String destMethodName = dm.getName();
						if (destMethodName.startsWith("set") && destMethodName.substring(3)
								.equals(srcMethodName.substring(3))) {
							dm.invoke(destination, getValue);
						}
					}
				} catch (Exception ignored) {
				}
			}
		}
		return destination;
	}


	public static <S, T> List<T> mappingList(List<S> src, List<T> target, Class<?> targetClass) {
		for (S s : src) {
			try {
				Object object = targetClass.newInstance();
				target.add((T) object);
				mapping(s, object);
			} catch (Exception ignored) {
			}
		}
		return target;
	}
}
