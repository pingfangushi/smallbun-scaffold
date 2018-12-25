package org.smallbun.framework.initialize;

import java.lang.annotation.*;

/**
 * 初始化注解
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/18 14:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Initialize {
}
