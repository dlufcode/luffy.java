package com.luffy.view.service.annotation;

import com.luffy.view.annotation.ClazzTag;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object object, String beanName) throws BeansException {
        Class clazz = object.getClass();
        ClazzTag clazzTag = (ClazzTag) clazz.getAnnotation(ClazzTag.class);
        if (clazzTag != null) {
            System.out.println("[class.Tag]:" + clazzTag.tag());
        }
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(Object object, String beanName) throws BeansException {
        return object;
    }
}
