package com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl;


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
    private String accountName;
    private String bankAccount;
    private String bankName;
    private String bankFullName;
    private String status;
    private String currency;
    private String isDefault;
    private String remark;


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
