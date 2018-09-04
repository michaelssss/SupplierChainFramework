package com.michaelssss.rzzl2.basicinfomanagement.goods;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.GoodsPropertyImpl;
import org.junit.Test;


/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */

public class Property extends SpringBootTestBasic {
    @Test
    public void addProperty() {
        GoodsProperty property = GoodsPropertyImpl.builder().propertyName("cpu").build();
        property.saveInfo();
        property.deleteInfo();
    }

    @Test
    public void updateProperty() {
        GoodsProperty property = GoodsPropertyImpl.builder().id(1l).propertyName("cpu").build();
        property.updateInfo();
    }

    @Test
    public void deleteProperty() {
        GoodsProperty property = GoodsPropertyImpl.builder().propertyName("cpu").build();
        property.saveInfo();
        property.deleteInfo();
    }
}
