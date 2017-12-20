package com.test.provider.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogging {

	private Log log = LogFactory.getLog(this.getClass());

	@Pointcut(value = "execution(public * com.test.provider.service..*.*(..))")
	public void myPointcut() {
	}

	@Before("myPointcut()")
	public void beforeService(JoinPoint joinPoint) {
		log.info("beforeService Logger");
		log.info("对象" + joinPoint.getTarget().getClass().getSimpleName());
		log.info("方法" + joinPoint.getSignature());
		Object[] a = joinPoint.getArgs();
		if (a != null && a.length != 0) {
			for (int i = 0; i < a.length; i++) {
				log.info("方法参数" + (i+1) + joinPoint.getArgs()[i]);
			}
		}else{
			log.info("无方法参数");
		}
	}

	@After("myPointcut()")
	public void afterService(JoinPoint joinPoint) {
		log.info("afterService Logger");
		System.out.println("afterService Logger");
	}
}

