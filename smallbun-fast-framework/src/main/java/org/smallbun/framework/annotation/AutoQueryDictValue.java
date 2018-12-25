package org.smallbun.framework.annotation;

import java.lang.annotation.*;

/**
 * 自动查询字典值
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/12/15 23:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AutoQueryDictValue {
}
