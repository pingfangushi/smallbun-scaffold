package org.smallbun.framework.annotation;

import java.lang.annotation.*;

/**
 * 演示环境
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/3 22:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface DemoEnvironment {
	/**
	 * 是否开启 默认true
	 */
	boolean open() default true;
}
