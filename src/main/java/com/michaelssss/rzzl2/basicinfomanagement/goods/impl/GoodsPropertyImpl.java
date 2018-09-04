package com.michaelssss.rzzl2.basicinfomanagement.goods.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.goods.GoodsProperty;
import com.michaelssss.rzzl2.basicinfomanagement.goods.repository.GoodsPropertyRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Builder
@Entity
@Data
@Table(name = "goods_property")
public class GoodsPropertyImpl implements GoodsProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "class_property", joinColumns = {@JoinColumn(name = "class_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "property_id")})
    private Set<GoodsClassImpl> goodsClasses;

    @Override
    public void saveInfo() {
        SpringContextHolder.getBean(GoodsPropertyRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(GoodsPropertyRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(GoodsPropertyRepository.class).delete(this.id);

    }
}
