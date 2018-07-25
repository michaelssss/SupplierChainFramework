package com.jzqh.rzzl2.contractmanagement.impl;

import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.contractmanagement.SalesContract;
import com.jzqh.rzzl2.contractmanagement.repository.SalesContractRepository;
import com.jzqh.rzzl2.exception.ExistException;
import com.jzqh.utils.BusinessCodeGenerator;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 销售合同
 * @Author:tanshaoxing
 * @Date:2018/7/18
 */
@Builder
@Entity
@Data
@Table(name = "sales_contract")
public class SalesContractImpl implements SalesContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectNo;//项目编号
    private String projectName;//项目名称
    private Long frameContractId;//主合同id
    private String frameContractNo;//主合同编号
    private String frameContractName;//主合同名称
    private Long salesOrderId;//销售订单id
    private String salesOrderNo;//销售订单编号
    private String salesOrderName;//销售订单名称
    private String salesContractName;//销售合同名称
    private String salesVersionNo;//版本号
    private String salesContractNo;//销售合同编号
    private String transportWay;//运输方式
    private String deliveryCostBearer;//运费承担
    private Date deliveryEndDate;//提货截止日期
    private BigDecimal amtTotal;//合计
    private String amtBlockTotal;//合计大写
    private String currency;//币种
    private Date effectiveTime;//合同生效时间
    private Date signTime;//合同签署时间
    private Date invalidTime;//合同截止时间
    private String auditState;//审批状态

    @Override
    public void addSalesContract() {
        SalesContractImpl frameContract = SalesContractImpl.builder().salesContractName(this.salesContractName).build();
        Example ex = Example.of(frameContract);
        frameContract = SpringContextHolder.getBean(SalesContractRepository.class).findOne(ex);
        if (frameContract != null) {
            throw new ExistException("合同名称已存在");
        }
        SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateSalesContract() {
        SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
    }

    @Override
    public void deleteSalesContract() {
        SpringContextHolder.getBean(SalesContractRepository.class).delete(this.id);
    }

    @Override
    public void confirmSalesContract() {
        this.setAuditState(SalesContract.Confirm);
        SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
    }

    @Override
    public String getSalesContract() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "HT-XS");

    }
}
