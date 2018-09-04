package com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.ContactRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;

@Entity
@Data
@Log4j
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactType;
    private String name;
    private String mobilePhone;
    private String phone;
    private String fax;
    private String email;
    private String department;
    private String position;
    private String remark;


    public boolean addInfo() {
        SpringContextHolder.getBean(ContactRepository.class).saveAndFlush(this);
        return false;
    }

    public boolean updateInfo() {
        SpringContextHolder.getBean(ContactRepository.class).saveAndFlush(this);
        return false;
    }

    public boolean deleteInfo() {
        //todo:删除失败的异常处理
        SpringContextHolder.getBean(ContactRepository.class).delete(this.id);
        log.info("");
        return false;
    }
}
