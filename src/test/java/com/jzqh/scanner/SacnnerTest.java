package com.jzqh.scanner;

import com.jzqh.SpringContextHolder;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.List;
import java.util.Set;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/30 10:58
 */
public class SacnnerTest extends SpringContextHolder {
    
    @Test
    public void scannerTest() throws ClassNotFoundException {
        Set<BeanDefinition> beanDefinitionSet = RouteScanner.getAllController("com.jzqh.rzzl2");
        List<String> filenames = RouteScanner.getFilename(beanDefinitionSet);
        RouteScanner.getRequestValue(filenames);
    }
    
}
