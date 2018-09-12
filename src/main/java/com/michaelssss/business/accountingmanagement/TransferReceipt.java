package com.michaelssss.business.accountingmanagement;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 转账流水
 */
@Entity
@Table(name = "transfer_receipt")
@Data
public class TransferReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @NotEmpty
    private String code;//流水单编码
    private Date transferDate;//转账日期
    private String payer;//转账人
    private String payerBank;//转账银行
    private String payerAccount;//转账账户
    private String payee;//收款人
    private String payeeBank;//收款人英航
    private String payeeAccount;//收款人账户
    private String currency;//币种
    private BigDecimal transferCharge;//转账费用
    private BigDecimal amount;//转账金额

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferReceipt)) return false;
        TransferReceipt that = (TransferReceipt) o;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode());
    }
}
