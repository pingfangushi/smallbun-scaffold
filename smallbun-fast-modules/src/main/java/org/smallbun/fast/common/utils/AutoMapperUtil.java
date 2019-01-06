package org.smallbun.fast.common.utils;

import org.smallbun.framework.toolkit.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/1/6 23:13
 */
public class AutoMapperUtil extends org.smallbun.framework.toolkit.AutoMapperUtil {
    /**
     * 树集合转VO集合
     *
     * @param src
     * @param target
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> mappingTreeList(List<S> src, List<T> target, Class<?> targetClass) {
        try {
            target = mappingList(src, target, targetClass);
            for (T vo : target) {
                Field children = ReflectionUtil.getFieldAll(vo, "children");
                children.setAccessible(true);
                if (vo != null && children.get(vo) != null) {
                    src = (List<S>) children.get(vo);
                    children.set(vo, mappingTreeList(src, new ArrayList<>(), targetClass));
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }
}
