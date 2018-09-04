package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_bank_account")
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
}
