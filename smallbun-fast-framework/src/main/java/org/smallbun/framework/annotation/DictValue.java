package org.smallbun.framework.annotation;

import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.annotation.*;

/**
 * 字典值注解，用于获取字典值
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/15 23:11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface DictValue {
	/**
	 *  字典类型编码
	 *
	 * @return {@link String}
	 */
	String typeCode();

	/**
	 *  列描述获取时对应的value字段名称
	 * @return {@link String}
	 */
	String valueField();
}
