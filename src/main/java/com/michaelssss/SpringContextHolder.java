package com.michaelssss;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 本类用于将SpringContext管理 如果自己的类不需要Spring管理，并且需要与Spring管理的实例交互，则需要用到此处
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
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
