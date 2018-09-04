package com.michaelssss.rzzl2.basicinfomanagement.goods.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.goods.GoodClass;
import com.michaelssss.rzzl2.basicinfomanagement.goods.repository.GoodClassRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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
@Table(name = "production_class")
public class GoodsClassImpl implements GoodClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String class_name;
    private String class_code;
    private String parent_code;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GoodsPropertyImpl> property;

    public void addGoodsProperty(GoodsPropertyImpl goodsProperty) {
        property = new HashSet<>();
        property.add(goodsProperty);
    }

    @Override
    public void saveInfo() {
        SpringContextHolder.getBean(GoodClassRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(GoodClassRepository.class).saveAndFlush(this);
    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(GoodClassRepository.class).delete(this.id);

    }
}
