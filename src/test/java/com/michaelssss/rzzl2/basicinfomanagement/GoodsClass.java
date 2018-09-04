package com.michaelssss.rzzl2.basicinfomanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsClassImpl;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsPropertyImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
public class GoodsClass extends SpringBootTestBasic {
    @Test
    public void addClass() {
        GoodsPropertyImpl goodsProperty = GoodsPropertyImpl.builder().propertyName("add").build();
        Set<GoodsPropertyImpl> goodsPropertySet = new HashSet<>();
        goodsPropertySet.add(goodsProperty);
        GoodClass goodsClass = GoodsClassImpl.builder().class_code("41001").class_name("发动机").parent_code("41").property(goodsPropertySet).
                build();
        goodsClass.saveInfo();


    }

    @Test
    public void updateClass() {
        GoodClass goodsClass = GoodsClassImpl.builder().id(1L).class_code("40001").class_name("通信设备").parent_code("40").
                build();
        goodsClass.saveInfo();
    }

    @Test
    public void deleteClass() {
        GoodsPropertyImpl goodsProperty = GoodsPropertyImpl.builder().propertyName("add").build();
        Set<GoodsPropertyImpl> goodsPropertySet = new HashSet<>();
        goodsPropertySet.add(goodsProperty);
        GoodClass goodsClass = GoodsClassImpl.builder().class_code("41001").class_name("发动机").parent_code("41").property(goodsPropertySet).
                build();
        goodsClass.saveInfo();
        goodsClass.deleteInfo();
    }
}
