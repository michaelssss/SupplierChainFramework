package com.michaelssss.rzzl2.contractmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.contractmanagement.PurchaseContract;
import com.michaelssss.rzzl2.contractmanagement.repository.PurchaseContactRepository;
import com.michaelssss.rzzl2.exception.ExistException;
import com.michaelssss.utils.BusinessCodeGenerator;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:采购合同实体类
 * @Author:tanshaoxing
 * @Date:2018/7/18
 */
@Builder
@Data
@Entity
@Table(name = "purchase_contract")
public class PurchaseContractImpl implements PurchaseContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mainContractId;//主合同id
    private String mainContractName;//主合同名称
    private String projectName;//项目名称
    private String versionNo;//合同版本号
    private String contractNo;//合同编号
    private String contractName;//合同名称
    private Long purchaseOrderId;//采购订单id
    private String purchaseOrderName;//采购订单名称
    private String purchaseOrderNo;//采购订单单号
    private String proxyCompany;//代理公司
    private Long warehouseId;//仓库id
    private String warehouseName;//仓库名称
    private Date deliveryTime;//交货时间
    private String qa;//货物质量确认人
    private Date prepaymentDatetime;//预付款时间
    private BigDecimal amtPrepayment;//预付款
    private BigDecimal amtPayBeforeDelivery;//交付前付款
    private Date balancePayDatetime;//余款支付时间
    private String balancePayWay;//余款支付方式
    private Date invoicingDatetime;//发票开具时间
    private String afterSalesServices;//售后服务
    private String overdueCondition;//逾期条件
    private String thirdParty;//第三方
    private Date contractSigningDate;//合同签订日期
    private Date contractStartDate;//合同开始日期
    private Date contractEndDate;//合同结束日期
    private String auditState = "1";//审批状态

    @Override
    public void addPurchaseContract() {
        PurchaseContractImpl frameContract = PurchaseContractImpl.builder().contractName(this.contractName).build();
        Example ex = Example.of(frameContract);
        frameContract = SpringContextHolder.getBean(PurchaseContactRepository.class).findOne(ex);
        if (frameContract != null) {
            throw new ExistException("合同名称已存在");
        }
        SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
    }

    @Override
    public void updatePurchaseContract() {
        SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
    }

    @Override
    public void deletePurchaseContract() {
        SpringContextHolder.getBean(PurchaseContactRepository.class).delete(this.id);
    }

    @Override
    public void confirmPurchaseContract() {
        this.setAuditState(PurchaseContract.Confirm);
        SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
    }

    @Override
    public String getPurchaseContractNo() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "HT-CG");
    }
}
