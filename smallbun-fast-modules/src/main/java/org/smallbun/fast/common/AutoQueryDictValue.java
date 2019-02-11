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

package org.smallbun.fast.common;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.common.entity.TreeDataEntity;
import org.smallbun.fast.manage.dict.service.SysDictValueService;
import org.smallbun.framework.annotation.DictValue;
import org.smallbun.framework.base.IAutoQueryDictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.smallbun.framework.toolkit.ReflectionUtil.getFieldAll;

/**
 * 自动注入字典值查询接口
 *
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
        if (!StringUtils.isEmpty(objects)) {
            for (Object object : objects) {
                autoQuery(object);
            }
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
        for (Field field : getFieldAll(object)) {
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            DictValue dictValue = field.getAnnotation(DictValue.class);
            if (!ObjectUtils.isEmpty(dictValue)) {
                map.put(field.getName(), dictValue.valueField());
            }
        }
        //移除不存在field名称的数据
        for (Field field : getFieldAll(object)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (field.getName().equals(entry.getValue())) {
                    fieldMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        //设置字段值
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            //获取列描述获取时对应的value字段名称
            Field declaredField = getFieldAll(object, entry.getValue());
            //设置对象的访问权限，保证对private的属性的访问
            declaredField.setAccessible(true);
            Object fieldValue = declaredField.get(object);
            //获取需要设置字典值的字段
            Field field = getFieldAll(object, entry.getKey());
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
        Class aClass = object.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (aClass != null) {
            aClass = aClass.getSuperclass();
            if (aClass != null) {
                if (aClass.equals(TreeDataEntity.class)) {
                    treeAutoQuery(object);
                    return;
                }
            }

        }

    }

    /**
     * 树处理
     *
     * @param object {@link Object}
     */
    private void treeAutoQuery(Object object) {
        List<Field> fieldAll = getFieldAll(object);
        for (Field field : fieldAll) {
            //处理children字段
            if ("children".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    //如果不等于空
                    if (!StringUtils.isEmpty(field.get(object))) {
                        autoQuery((List) field.get(object));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 注入字典值service接口
     */
    private final SysDictValueService valueService;
}
