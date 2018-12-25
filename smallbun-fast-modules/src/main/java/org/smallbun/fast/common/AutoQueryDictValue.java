package org.smallbun.fast.common;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.framework.annotation.DictValue;
import org.smallbun.framework.base.IAutoQueryDictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 自动注入字典值查询接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/16 11:38
 */
@Slf4j
@Component
public class AutoQueryDictValue implements IAutoQueryDictValue {
	@Autowired
	public AutoQueryDictValue(SysDictValueService valueService) {
		this.valueService = valueService;
	}

	/**
	 * 自动查询集合实例方法
	 *
	 * @param objects 方法返回值
	 */
	@Override
	public void autoQuery(List<Object> objects) throws Exception {
		for (Object object : objects) {
			autoQuery(object);
		}
	}

	/**
	 * 自动查询单个实例方法,目前性能较慢
	 *
	 * @param object 方法返回值
	 */
	@Override
	public void autoQuery(Object object) throws Exception {
		//使用线程安全的ConcurrentMap
		Map<String, String> map = Maps.newConcurrentMap();
		Map<String, String> fieldMap = Maps.newConcurrentMap();
		//获取所有的字段，将需要设置字典的属性为key，将获取值的属性为value放入map
		for (Field field : object.getClass().getDeclaredFields()) {
			//设置对象的访问权限，保证对private的属性的访问
			field.setAccessible(true);
			DictValue dictValue = field.getAnnotation(DictValue.class);
			if (!ObjectUtils.isEmpty(dictValue)) {
				map.put(field.getName(), dictValue.valueField());
			}
		}
		//移除不存在field名称的数据
		for (Field field : object.getClass().getDeclaredFields()) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (field.getName().equals(entry.getValue())) {
					fieldMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		//设置字段值
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			//获取列描述获取时对应的value字段名称
			Field declaredField = object.getClass().getDeclaredField(entry.getValue());
			//设置对象的访问权限，保证对private的属性的访问
			declaredField.setAccessible(true);
			Object fieldValue = declaredField.get(object);
			//获取需要设置字典值的字段
			Field field = object.getClass().getDeclaredField(entry.getKey());
			DictValue dictValue = field.getAnnotation(DictValue.class);
			if (!ObjectUtils.isEmpty(dictValue)) {
				//获取并设置值
				field.setAccessible(true);
				String label = valueService.findLabelByTypeCodeAndValue(dictValue.typeCode(), fieldValue.toString());
				if (!ObjectUtils.isEmpty(label)) {
					//如果是String类型
					if (field.getType().equals(String.class)) {
						field.set(object, label);
					}
				}
			}
		}
	}

	/**
	 * 注入字典值service接口
	 */
	private final SysDictValueService valueService;
}
