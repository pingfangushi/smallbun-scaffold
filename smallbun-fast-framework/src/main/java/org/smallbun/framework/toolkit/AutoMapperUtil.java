/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.framework.toolkit;

import java.lang.reflect.Method;
import java.util.List;

/**
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
