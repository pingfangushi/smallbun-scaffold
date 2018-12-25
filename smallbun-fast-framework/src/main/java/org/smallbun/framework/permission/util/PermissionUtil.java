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
