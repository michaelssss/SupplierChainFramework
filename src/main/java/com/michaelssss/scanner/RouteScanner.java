package com.michaelssss.scanner;

import com.michaelssss.account.FunctionName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 本类用于查找当前项目被Api及RequestMapping注解的类，用于注册服务
 *
 * @author Tongch
 * @version 1.0
 * @time 2018/7/30 9:56
 * -----------------------------------------------
 * @modifyBy Michaelssss
 * @modifyTime 2018/9/4 8:53
 */
@Slf4j
class RouteScanner {
    //此处与spring组件耦合,测试时候需要将Spring初始化
    Set<FunctionName> getAllFunctionNames(String path) {
        Set<BeanDefinition> beanDefinitions = findAllBeanDefineByFunction(path);
        Set<String> clazzNames = new HashSet<>();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            clazzNames.add(beanDefinition.getBeanClassName());
        }
        Collection<Class<?>> classes = getClassesByName(clazzNames);
        return getFunctionNames(classes);
    }

    private Set<FunctionName> getFunctionNames(Collection<Class<?>> classes) {
        Set<FunctionName> functionNames = new HashSet<>();
        //以上是预备工作
        //以下循环开始正式的搜索所有的注解
        for (Class<?> clazz : classes) {
            //先找当前的class是否有class注解
            Api function = clazz.getAnnotation(Api.class);
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            if (!hasAnnotations(clazz, Api.class, RequestMapping.class)) {
                log.error(clazz.getSimpleName() + " is not a Standard Controller define in this System");
                continue;
            }
            FunctionName functionName = new FunctionName();
            functionName.setUrl("/" + requestMapping.value()[0]);
            functionName.setFunctionName(function.value());
            functionNames.add(functionName);
            //搜索当前Class的Function是否有Function注解
            Method[] methods = null;
            try {
                methods = clazz.getMethods();
            } catch (SecurityException e) {
                log.info("SecurityException ", e);
            }
            //有可能返回空置，但是要做循环保护
            if (null != methods) {
                for (Method method : methods) {
                    if (hasAnnotations(method, ApiOperation.class, RequestMapping.class)) {
                        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        FunctionName functionName1 = new FunctionName();
                        functionName1.setFunctionName(function.value() + "." + apiOperation.value());
                        functionName1.setUrl("/" + requestMapping.value()[0] + "/" + methodRequestMapping.value()[0]);
                        functionNames.add(functionName1);
                    }
                }
            }
        }
        return functionNames;
    }

    @SafeVarargs
    private final boolean hasAnnotations(Method method, Class<? extends Annotation>... clazzes) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        int flag = 0;
        for (Annotation annotation : annotations) {
            for (Class<? extends Annotation> annotationClazz : clazzes) {
                if (annotation.annotationType().equals(annotationClazz)) {
                    flag++;
                }
            }
        }
        return Integer.compare(flag, clazzes.length) == 0;
    }

    @SafeVarargs
    private final boolean hasAnnotations(Class clazz, Class<? extends Annotation>... clazzes) {
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        int flag = 0;
        for (Annotation annotation : annotations) {
            for (Class<? extends Annotation> annotationClazz : clazzes) {
                if (annotation.annotationType().equals(annotationClazz)) {
                    flag++;
                }
            }
        }
        return Integer.compare(flag, clazzes.length) == 0;
    }

    private Collection<Class<?>> getClassesByName(Set<String> clazzNames) {
        Set<Class<?>> classes = new HashSet<>();
        for (String clazz : clazzNames) {
            try {
                classes.add(Class.forName(clazz));
            } catch (ClassNotFoundException e) {
                log.info(clazz + " Not Found");
            }
        }
        return classes;
    }

    /**
     * 查找当前路径下所有的@Api注解文件
     *
     * @param path 格式为 com.michaelssss.rzzl2
     * @return
     */
    private Set<BeanDefinition> findAllBeanDefineByFunction(String path) {

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Api.class));

        return scanner.findCandidateComponents(path);
    }

}
