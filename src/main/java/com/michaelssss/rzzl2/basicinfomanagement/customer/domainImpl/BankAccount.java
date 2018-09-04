package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.BankAccountRepository;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;//账户名称
    private String bankAccount;//银行账号
    private String bankName;//开户行名称
    private String bankFullName;//开户行全称
    private String status;//账号状态
    private String currency;//币种
    private String isDefault;//是否为默认账户
    private String remark;//备注


    public BankAccount saveInfo() {
        return SpringContextHolder.getBean(BankAccountRepository.class).saveAndFlush(this);
    }


    public boolean updateInfo() {
        SpringContextHolder.getBean(BankAccountRepository.class).saveAndFlush(this);
        return false;
    }


    public boolean deleteInfo() {
        SpringContextHolder.getBean(BankAccountRepository.class).delete(this.id);
        return false;
    }
}
