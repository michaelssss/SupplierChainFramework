package com.jzqh.rzzl2.basicinfomanagement.goods.impl;

import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.goods.PropertyValue;
import com.jzqh.rzzl2.basicinfomanagement.goods.repository.PropertyValueRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
@Builder
@Entity
@Data
@Table(name = "property_value")
public class PropertyValueImpl implements PropertyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyValue;
    private Long propertyId;

    @Override
    public void saveInfo() {
        SpringContextHolder.getBean(PropertyValueRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(PropertyValueRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(PropertyValueRepository.class).delete(this.id);

    }
}
