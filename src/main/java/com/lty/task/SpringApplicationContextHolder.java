package com.lty.task;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.Validate.notEmpty;

/**
 * 在不由spirng管理对象对注入由spring管理的对象
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationContextHolder.context = context;
    }


    public static Object getSpringBean(String beanName) {
        notEmpty(beanName, "bean name is required");
        return context==null?null:context.getBean(beanName);
    }

    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }
}
