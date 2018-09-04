package com.michaelssss;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext paramApplicationContext)
            throws BeansException {
        context = paramApplicationContext;
    }
}