package org.smallbun.framework.permission.annotation;

import java.lang.annotation.*;


/**
 * 数据权限过滤
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 10:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScopeFilter {

	String value() default "";

}
