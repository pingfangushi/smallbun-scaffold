package org.smallbun.framework.base;

import java.util.List;

/**
 * 自动注入字典值,查询接口，继承并重写此接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/16 11:34
 */
public interface IAutoQueryDictValue {
	/**
	 * 自动查询单个实例方法
	 *
	 * @param object 方法返回值
	 * @throws Exception
	 */
	void autoQuery(Object object) throws Exception;

	/**
	 * 自动查询集合实例方法
	 *
	 * @param objects 方法返回值
	 * @throws Exception
	 */
	void autoQuery(List<Object> objects) throws Exception;
}
