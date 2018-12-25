package org.smallbun.framework.toolkit;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author yuqiyu
 * ===============================
 * Created with IntelliJ IDEA.
 * User：于起宇
 * Date：2017/8/12
 * Time：9:54
 * 简书：http://www.jianshu.com/u/092df3f77bca
 * ================================
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
