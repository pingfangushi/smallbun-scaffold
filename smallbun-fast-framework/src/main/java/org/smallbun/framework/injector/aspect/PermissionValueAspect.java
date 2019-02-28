package org.smallbun.framework.injector.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.smallbun.framework.base.BaseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static org.smallbun.framework.toolkit.ReflectionUtil.getFieldAll;

/**
 * 权限值注入
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-02-28 15:01
 */
@Slf4j
@Aspect
@Component
public class PermissionValueAspect {

	/**
	 * 切入Mapper层
	 */
	@Pointcut("execution(* org.smallbun.*.manage.*.dao.*Mapper.*(..)) || execution(* org.smallbun.*.manage.*.*.dao.*Mapper.*(..))")
	private void method() {
	}


	@Around("method()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			//如果包含QueryWrapper
			if (arg instanceof QueryWrapper) {
				Object entity = ((QueryWrapper) arg).getEntity();
				if (entity instanceof BaseEntity) {
					Field permissionType = getFieldAll(entity, "permissionType");
					permissionType.setAccessible(true);
					//设置用户权限类型
					permissionType.set(entity, "1");
				}
			}
		}
		return pjp.proceed();
	}
}
