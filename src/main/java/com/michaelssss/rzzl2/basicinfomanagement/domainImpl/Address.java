package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
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

}
