package com.michaelssss.rzzl2.basicinfomanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsClassImpl;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsPropertyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.ProductionInfoImpl;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.PropertyValueImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
public class ProductionInfoTest extends SpringBootTestBasic {
    @Test
    public void addGoodsInfo() {
        GoodsPropertyImpl goodsProperty = GoodsPropertyImpl.builder().propertyName("add").build();
        Set<GoodsPropertyImpl> goodsPropertySet = new HashSet<>();
        goodsPropertySet.add(goodsProperty);
        GoodsClassImpl goodsClass = GoodsClassImpl.builder().class_code("41001").class_name("发动机").parent_code("41").property(goodsPropertySet).
                build();

        Set<GoodsPropertyImpl> property = goodsClass.getProperty();
        Set<PropertyValueImpl> propertyValueSet = new HashSet();
        for (GoodsPropertyImpl s : property) {
            PropertyValueImpl propertyValue = PropertyValueImpl.builder().build();
            propertyValue.setPropertyValue("红");
            propertyValueSet.add(propertyValue);
        }

        ProductionInfo goodsInfo = ProductionInfoImpl.builder().state("").brands("华为").description("华为2p0为你所想")
                .industry("深圳工厂").model("64g").partNumber("1001").pushOnlineShop("1").goodsClass(goodsClass).propertyValueSet(propertyValueSet).name("华为p20")
                .build();
        ((ProductionInfoImpl) goodsInfo).setPartNumber(goodsInfo.getPartNumberNo());
        goodsInfo.saveInfo();

        goodsInfo.deleteInfo();
    }

    @Test
    public void updateGoodInfo() {
        GoodsPropertyImpl goodsProperty = GoodsPropertyImpl.builder().propertyName("add").build();
        Set<GoodsPropertyImpl> goodsPropertySet = new HashSet<>();
        goodsPropertySet.add(goodsProperty);
        GoodsClassImpl goodsClass = GoodsClassImpl.builder().class_code("41001").class_name("发动机").parent_code("41").property(goodsPropertySet).
                build();
        Set<GoodsPropertyImpl> property = goodsClass.getProperty();
        Set<PropertyValueImpl> propertyValueSet = new HashSet();
        for (GoodsPropertyImpl s : property) {
            PropertyValueImpl propertyValue = PropertyValueImpl.builder().build();
            propertyValue.setPropertyValue("黑");
            propertyValueSet.add(propertyValue);
        }
        ProductionInfo goodsInfo = ProductionInfoImpl.builder().state("").brands("华为测试修改").description("华为2p0为你所想")
                .industry("深圳工厂").model("64g").partNumber("1001").pushOnlineShop("1").name("华为p20").goodsClass(goodsClass).propertyValueSet(propertyValueSet).build();
        goodsInfo.saveInfo();
        goodsInfo.updateInfo();
    }

    @Test
    public void deleteGoods() {

        ProductionInfo goodsInfo = ProductionInfoImpl.builder().state("").brands("华为测试修改").description("华为2p0为你所想")
                .industry("深圳工厂").model("64g").partNumber("1001").pushOnlineShop("1").name("华为p20")
                .build();
        goodsInfo.saveInfo();
        goodsInfo.deleteInfo();
    }
}
