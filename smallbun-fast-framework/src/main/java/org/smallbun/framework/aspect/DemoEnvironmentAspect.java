package org.smallbun.framework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.smallbun.framework.annotation.DemoEnvironment;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.exception.BusinessExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 演示环境切面
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/3 22:46
 */
@Slf4j
@Aspect
@Component
public class DemoEnvironmentAspect {

	private final SmallBunProperties smallBunProperties;

	@Autowired
	public DemoEnvironmentAspect(SmallBunProperties smallBunProperties) {
		this.smallBunProperties = smallBunProperties;
	}

	/**
	 * 演示环境切面
	 * @param pjp
	 * @param demoEnvironment
	 * @return
	 */
	@Around("@annotation(demoEnvironment)")
	public Object demo(ProceedingJoinPoint pjp, DemoEnvironment demoEnvironment) throws Throwable {
		//1.如果系统没开启演示环境，忽略注解
		if (smallBunProperties.getDemo().isOpen()) {
			log.info("---------------------------演示环境拦截---------------------------");
			throw new BusinessExecption(String.valueOf(HttpStatus.FORBIDDEN), "演示环境不允许操作");
		}
		//2.如果系统整体开启了，根据注解值进行判断是否拦截并提示，演示环境不允许操作

		boolean open = demoEnvironment.open();
		if (open) {
			log.info("---------------------------演示环境拦截---------------------------");
			throw new BusinessExecption(String.valueOf(HttpStatus.FORBIDDEN), "演示环境不允许操作");
		}
		return pjp.proceed();
	}
}
