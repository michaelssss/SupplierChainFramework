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
@Table(name = "company_contact")
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
}
