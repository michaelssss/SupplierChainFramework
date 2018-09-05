package com.michaelssss.rzzl2.creditmanagement;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "credit", indexes = {@Index(name = "idx_companyName", columnList = "companyFullName", unique = true)})
@Data
public class CreditImpl implements Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 64, unique = true)
    private String companyFullName;
    private String auditReplyCode;
    private String type;
    private BigDecimal useLimit;
    private String currency;
    private String status;
    private Integer duration;
    private Date startDate;
    private Date lastModificationDate;
    private String remark;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value = "operation_date")
    private List<UseCreditSerial> useCreditSerials;


    @Override
    public void limitIncrease(Credit credit) {
        if (credit.getCreditCompany().equals(this.companyFullName)) {
            useLimit = useLimit.add(credit.getCreditLimitValue());
        } else {
            throw new RuntimeException("该credit没有指明公司");
        }
    }

    @Override
    public void limitDecrease(Credit credit) {
        if (credit.getCreditCompany().equals(this.companyFullName)) {
            useLimit = useLimit.subtract(credit.getCreditLimitValue());
        } else {
            throw new RuntimeException("该credit没有指明公司");
        }
    }

    @Override
    public BigDecimal getCreditLimitValue() {
        return this.useLimit;
    }

    @Override
    public BigDecimal getCreditRemainValue() {
        return getCreditLimitValue().subtract(getInOccupyMoney());
    }

    private BigDecimal getInOccupyMoney() {
        BigDecimal result = BigDecimal.ZERO;
        for (UseCreditSerial useCreditSerial : this.useCreditSerials) {
            result = result.add(useCreditSerial.getOccupation());
        }
        return result;
    }

    @Override
    public String getCreditCompany() {
        return this.companyFullName;
    }

    @Override
    public void useCredit(BigDecimal creditValue, String code) throws OverCreditLimitException {
        if (creditValue.compareTo(getCreditRemainValue()) < 1) {
            throw new OverCreditLimitException();
        }
        UseCreditSerial useCreditSerial = new UseCreditSerial();
        useCreditSerial.setType("");
        useCreditSerial.setOccupation(creditValue);
        useCreditSerial.setOrderCode(code);
        useCreditSerial.setOperationDate(new Date());
        this.useCreditSerials.add(useCreditSerial);
    }
}
