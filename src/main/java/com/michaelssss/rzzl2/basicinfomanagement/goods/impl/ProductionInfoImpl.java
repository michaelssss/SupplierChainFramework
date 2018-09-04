package com.michaelssss.rzzl2.basicinfomanagement.goods.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.goods.ProductionInfo;
import com.michaelssss.rzzl2.basicinfomanagement.goods.repository.ProductionRepository;
import com.michaelssss.utils.BusinessCodeGenerator;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/9
 */
@Builder
@Entity
@Data
@Table(name = "goods")
public class ProductionInfoImpl implements ProductionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partNumber;//商品料号
    private String name;
    private String description;//商品简称
    private String brands;
    private String model;//型号
    private String standard;
    private String pushOnlineShop;//是否上架
    private Date startTime;
    private Date endTime;
    private String remark;
    private String state;
    private String industry;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GoodsClassImpl goodsClass;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsId")
    private Set<PropertyValueImpl> propertyValueSet;


    @Override
    public void saveInfo() {
        SpringContextHolder.getBean(ProductionRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(ProductionRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(ProductionRepository.class).delete(this.id);
    }

    @Override
    public String getPartNumberNo() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "SP");

    }


}
