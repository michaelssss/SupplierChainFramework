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
@Table(name = "company_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressType;
    private String province;
    private String city;
    private String area;
    private String detail;
    private String zipCode;//邮政编码
    private String connectPeople;
    private String connectPhone;
    private String connectEmail;
    private String isDefault;
    private String remark;
}
