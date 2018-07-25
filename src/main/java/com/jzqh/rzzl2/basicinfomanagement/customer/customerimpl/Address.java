package com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl;


import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.AddressRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "company_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long addressType;
    private Long province;
    private Long city;
    private Long area;
    private String detail;
    private String zipCode;//邮政编码
    private String connectPeople;
    private String connectPhone;
    private String connectEmail;
    private String isDefault;
    private String remark;


    public boolean saveInfo() {
        SpringContextHolder.getBean(AddressRepository.class).saveAndFlush(this);//返回保存后的实体
        return false;
    }


    public boolean updateInfo() {
        SpringContextHolder.getBean(AddressRepository.class).saveAndFlush(this);
        return false;
    }


    public boolean deleteInfo() {
        SpringContextHolder.getBean(AddressRepository.class).delete(this.id);
        return false;
    }
}
