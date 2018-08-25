package com.jzqh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertiesUtil {
    public static String getValue(String key){

        Properties prop = new Properties();

        // 使用class.getClass()所得到的java.lang.ClassLoader的getResourceAsStream()方法
        InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/salt.properties");

        try {
            // 用流来加载配置文件
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 指定键名来获取值
        return prop.getProperty(key);
    }
}
