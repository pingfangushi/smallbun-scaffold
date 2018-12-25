package org.smallbun.framework.initialize;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 监听器处理器
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/18 19:42
 */
@Component
public class ListenerProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(@Nullable Object bean, String beanName) throws BeansException {
		assert bean != null;
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
		for (Method method : methods) {
			Initialize initialize = AnnotationUtils.findAnnotation(method, Initialize.class);
			if (Objects.nonNull(initialize)) {

			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) throws BeansException {

		return bean;
	}

}
