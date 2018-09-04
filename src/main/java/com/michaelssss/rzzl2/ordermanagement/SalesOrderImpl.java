package com.michaelssss.rzzl2.ordermanagement;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.utils.BusinessCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_order")
@Setter
@Getter
@ToString(callSuper = true)
@DiscriminatorValue("SALES_ORDER")
class SalesOrderImpl extends AbstractOrder implements SalesOrder {
    private String entrustedCode;//委托订单编号
    private String buyer;//买家名称
    private String paymentType;//付款方式
    private String currency;//币种
    private String source;//订单来源
    private BigDecimal serviceCharge;//服务费
    private BigDecimal prePayTotal;//预付款金额
    private BigDecimal guaranteeMoneyOffset;//保证金抵扣金额
    private BigDecimal actualPayTotal;//实际支付金额
    private String payChannel;//支付渠道：现金，网银，线下转账……

    @Override
    protected String generateCode() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "XS");
    }
}
