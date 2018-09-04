package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "company_shareholder_info")
public class ShareholderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shareholderName;
    private String fundedOfRatio;
    private String contribution;
    private String currency;
    private String investmentOfType;
    private String remark;
}
