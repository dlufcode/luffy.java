package com.luffy.view.aspect;

import com.luffy.view.annotation.MethodEnd;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MethodEndAspect {

    @Pointcut("@annotation(com.luffy.view.annotation.MethodEnd)")
    private void pointcut() {

    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println(String.format("%s[afterReturning]=============", ""));
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
            Object target = proceedingJoinPoint.getTarget();
            Class clazz = target.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            String name = "null";
            for (Method method : methods) {
                MethodEnd methodEnd = AnnotationUtils.getAnnotation(method, MethodEnd.class);
                if (methodEnd == null) {
                    continue;
                }
                name = methodEnd.name();
            }
            System.out.println(String.format("[%s][around]=============", name));
            //
            for (Method method : methods) {
                MethodEnd methodEnd = method.getAnnotation(MethodEnd.class);
                if (methodEnd == null) {
                    continue;
                }
                System.out.println(String.format("[name]:%s", methodEnd.name()));
            }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }


}
