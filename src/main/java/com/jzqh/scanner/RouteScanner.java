package com.jzqh.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/30 9:56
 */
@Slf4j
public class RouteScanner {

    private String path;

    public RouteScanner(String path) {
        this.path = path;
    }

    Set<String> getAllUrl() {
        Set<BeanDefinition> beanDefinitions = getAllController(path);
        List<String> filenames = getFilename(beanDefinitions);
        try {
            return getAllUrl(filenames);
        } catch (Exception e) {
            log.debug("", e);
        }
        return new HashSet<>();
    }

    /**
     * 查找当前路径下所有的@controller注解文件
     *
     * @param path 格式为 com.jzqh.rzzl2
     * @return
     */
    private Set<BeanDefinition> getAllController(String path) {

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

        //String格式为com.jzqh.rzzl2
        Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(path);

        return beanDefinitionSet;

    }

    /**
     * 将Set集合中的文件名放入到list集合中并返回
     *
     * @param beanDefinitionSet
     * @return
     */
    private List<String> getFilename(Set<BeanDefinition> beanDefinitionSet) {
        List<String> filenames = new ArrayList<>();
        if (beanDefinitionSet != null) {
            for (BeanDefinition beanDefinition : beanDefinitionSet) {
                filenames.add(beanDefinition.getBeanClassName());
            }
        }
        return filenames;
    }

    /**
     * 根据反射得到文件中的所有@requestMapping的value
     *
     * @param filenames
     * @throws ClassNotFoundException
     */
    private void showRequestValue(List<String> filenames) throws ClassNotFoundException {
        //得到字节码文件  【只需要更改controller类名】
        log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                "  共有" + filenames.size() + "个拥有Controller注解的文件");
        for (int i = 0; i < filenames.size(); i++) {
            log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                    "   在" + filenames.get(i).substring(filenames.get(i).lastIndexOf(".") + 1) + "类中：");
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
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    //输出 annotation RequestMapping包含的信息(headers=[], name=, path=[], value=[toTicket], produces=[], method=[], params=[], consumes=[])
                    String[] valuesList = annotation.value();
                    for (String value : valuesList) {
                        values.add(value);
                    }
                }
            }
            if (n == 0) {
                log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 当前类不存在requestMapping注解");
            } else {
                log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() + "  总共有" + values.size() + "个requestMapping注解");
                log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() + "  其值分别为：  ");
                for (int j = 0; j < values.size(); j++) {
                    log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() + values.get(j) + ", ");
                }
            }
        }
    }

    private Set<String> getAllUrl(List<String> filenames) throws ClassNotFoundException {
        Set<String> urlSet = new HashSet<>();
        log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() + "  共有  "
                + filenames.size() + "  个拥有Controller注解的文件");
        for (int i = 0; i < filenames.size(); i++) {
            Class<?> clazz = Class.forName(filenames.get(i));
            log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                    "第 " + (i + 1) + " 个Controller类 ： " + filenames.get(i).substring(filenames.get(i).lastIndexOf(".") + 1));
            //判断类class中是否存在requestMapping注解
            if (clazz.isAnnotationPresent(RequestMapping.class)) {

                //得到RequestMapping的value值
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                String[] prefixValues = annotation.value();

                if (prefixValues != null && prefixValues.length > 0) {
                    //规定每个class中只有一个@requestMapping注解作为类注释
                    String prefix = prefixValues[0];//获取类路由
                    //保存类路由
                    urlSet.add("/"+prefix);
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {

                        //判断类方法中是否存在requestMapping注解
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                            String[] values = annotation1.value();
                            if (values != null && values.length > 0) {
                                for (String value : values) {
                                    //保存方法路由
                                    urlSet.add("/" + prefix + "/" + value);
                                }
                            }
                        }
                    }
                } else {
                    log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                            "当前文件路由格式不正确，请在@requestMapping注解中添加value值");
                }
            } else {
                log.debug("当前方法为：" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                        "当前类路由格式不正确，请在类中添加@requestMapping注解");
            }
        }
        return urlSet;
    }

}
