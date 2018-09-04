package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;

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
    private String contactType;//联系人类型
    private String name;//联系人姓名
    private String mobilePhone;//移动手机号码
    private String phone;//固话
    private String fax;//传真
    private String email;//邮件
    private String department;//联系人所属部门
    private String position;//职位
    private String remark;//备注


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
