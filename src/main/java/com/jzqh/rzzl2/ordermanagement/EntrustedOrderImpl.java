package com.jzqh.rzzl2.ordermanagement;

import com.jzqh.SpringContextHolder;
import com.jzqh.utils.BusinessCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entrusted_order")
@Getter
@Setter
@ToString(callSuper = true)
@DiscriminatorValue("ENTRUSTED_ORDER")
class EntrustedOrderImpl extends AbstractOrder implements EntrustedOrder {

    private String entrustedCompany;//委托公司
    private String mainContractId;//框架合同编号
    private String mainContractName;//框架合同名称
    private String supplierName;//供应商名称
    private String paymentDate;//垫付日期
    private BigDecimal paymentMoney;//垫付金额
    private String currency;//币种
    private String supplierContactName;//供应商联系人
    private String supplierContactPhone;//供应商联系人电话
    private String deliveryMethod;//配送方式
    private Date pickDate;//提货日期
    private String deliveryCostBearer;//配送费用承担方
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ReceivedInfo receivedInfo;

    @Override
    protected String generateCode() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "WT");
    }
}
