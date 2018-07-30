package com.jzqh.scanner;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/30 9:56
 */
public class RouteScanner {
    
    
    /**
     *  查找当前路径下所有的@controller注解文件
     * @param path 格式为 com.jzqh.rzzl2
     * @return
     */
    public static Set<BeanDefinition> getAllController(String path){
    
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
    
        //String格式为com.jzqh.rzzl2
        Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(path);
        
        return beanDefinitionSet;
        
    }
    
    /**
     * 将Set集合中的文件名放入到list集合中并返回
     * @param beanDefinitionSet
     * @return
     */
    public static List<String> getFilename(Set<BeanDefinition> beanDefinitionSet){
        List<String> filenames = new ArrayList<>();
        if(beanDefinitionSet != null) {
            for (BeanDefinition beanDefinition : beanDefinitionSet) {
                filenames.add(beanDefinition.getBeanClassName());
            }
        }
        return  filenames;
    }
    
    /**
     * 根据反射得到文件中的所有@requestMapping的value
     * @param filenames
     * @throws ClassNotFoundException
     */
    public static void getRequestValue(List<String> filenames) throws ClassNotFoundException {
        //得到字节码文件  【只需要更改controller类名】
        System.out.println("共有" + filenames.size()+"个拥有Controller注解的文件");
        System.out.println();
        for (int i = 0; i < filenames.size(); i++) {
            System.out.println("在" + filenames.get(i).substring(filenames.get(i).lastIndexOf(".") + 1)
                    + "类中：");
            //获取当前类的反射
            Class<?> clazz = Class.forName(filenames.get(i));
            //得到方法
            Method[] methods = clazz.getDeclaredMethods();
            int n = 0;
            ArrayList<String> values = new ArrayList<>();
            for (Method method : methods) {
                boolean annotationPresent = method.isAnnotationPresent(RequestMapping.class);
                //判断是否存在requestmapping的注释
                if (annotationPresent) {
                    //得到requestmapping的注释
                    n++;
                    //System.out.println("存在requestMapping注解,第"+n+"个值为：");
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    //输出 annotation RequestMapping包含的信息(headers=[], name=, path=[], value=[toTicket], produces=[], method=[], params=[], consumes=[])
                    //System.err.println(annotation);
                    String[] valuesList = annotation.value();
                    for (String value : valuesList) {
                        values.add(value);
                    }
                }
            }
            if (n == 0) {
                System.out.println("当前类不存在requestMapping注解");
                System.out.println();
            } else {
                System.out.println("总共有" + values.size() + "个requestMapping注解");
                System.out.print("其值分别为：  ");
                for (int j = 0; j < values.size(); j++) {
                    //System.out.println("总共有" + values.size() + "个requestMapping注解");
                    //System.out.print("其值分别为：  ");
                    System.out.print(values.get(j) + ", ");
                }
                System.out.println();
                System.out.println();
            }
        }
    }
    
}
