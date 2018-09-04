package com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.EntryApplySupplierRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "entry_apply_supplier")
public class EntryApplySupplierImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplierId;
    private String partnerName;//合作伙伴名称
    private String legalRepresentative;//法人
    private String actualController;//实际控制人
    private BigDecimal registeredCapital;//注册资本
    private BigDecimal registerdCapital;//实缴资本
    private String registeredAddress;//注册地址
    private String partnerNature;//合作伙伴性质
    private String currency;//币种
    private String businessLicense;//企业营业执照
    private String idCard;//法人身份证复印件
    private String officePicture;//办公场所照片
    private String contactName;//联系人
    private String position;//职务
    private String contactPhone;//联系方式
    private String scope;//经营范围
    private String taxRating;//纳税评级
    private String changeRecord;//修改记录
    private Date accessTime;//准入时间
    private String validityOfAdmission;//准入有效期
    private String accessStation;//准入状态
    private Date registeredDate;//注册日期


    public void addInfo() {
        SpringContextHolder.getBean(EntryApplySupplierRepository.class).saveAndFlush(this);

    }


    public void updateInfo() {
        SpringContextHolder.getBean(EntryApplySupplierRepository.class).saveAndFlush(this);

    }


    public void deleteInfo(Long id) {
        SpringContextHolder.getBean(EntryApplySupplierRepository.class).delete(id);

    }


    public void requestPermit() {
        this.updateInfo();
    }
}
