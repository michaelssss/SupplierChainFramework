package com.michaelssss.business.contractmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.contractmanagement.Contract;
import com.michaelssss.business.contractmanagement.repository.PurchaseContactRepository;
import com.michaelssss.utils.BusinessCodeGenerator;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Example;

/**
 * @Description:采购合同实体类 @Author:tanshaoxing @Date:2018/7/18
 */
@Builder
@Data
@Entity
@Table(name = "purchase_contract")
public class PurchaseContractImpl implements Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long mainContractId; // 主合同id
  private String mainContractName; // 主合同名称
  private String projectName; // 项目名称
  private String versionNo; // 合同版本号
  private String contractNo; // 合同编号
  private String contractName; // 合同名称
  private Long purchaseOrderId; // 采购订单id
  private String purchaseOrderName; // 采购订单名称
  private String purchaseOrderNo; // 采购订单单号
  private String proxyCompany; // 代理公司
  private Long warehouseId; // 仓库id
  private String warehouseName; // 仓库名称
  private Date deliveryTime; // 交货时间
  private String qa; // 货物质量确认人
  private Date prepaymentDatetime; // 预付款时间
  private BigDecimal amtPrepayment; // 预付款
  private BigDecimal amtPayBeforeDelivery; // 交付前付款
  private Date balancePayDatetime; // 余款支付时间
  private String balancePayWay; // 余款支付方式
  private Date invoicingDatetime; // 发票开具时间
  private String afterSalesServices; // 售后服务
  private String overdueCondition; // 逾期条件
  private String thirdParty; // 第三方
  private Date contractSigningDate; // 合同签订日期
  private Date contractStartDate; // 合同开始日期
  private Date contractEndDate; // 合同结束日期
  private String auditState = "1"; // 审批状态

  @Override
  public void add() {
    PurchaseContractImpl frameContract =
        PurchaseContractImpl.builder().contractName(this.contractName).build();
    Example ex = Example.of(frameContract);
    if (SpringContextHolder.getBean(PurchaseContactRepository.class).exists(ex)) {
      throw new BusinessException("合同名称已存在");
    }
    SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
  }

  @Override
  public void update() {
    if (!this.auditState.equals(EDITABLE)) {
      throw new BusinessException("合同不可修改状态");
    }
    SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
  }

  @Override
  public void apply() {
    // TODO:销售合同发起审批
  }

  @Override
  public void confirm() {
    this.setAuditState(Contract.CONFIRM);
    SpringContextHolder.getBean(PurchaseContactRepository.class).saveAndFlush(this);
  }

  private String getPurchaseContractNo() {
    return SpringContextHolder.getBean(BusinessCodeGenerator.class)
        .getSequence(this.getClass(), "HT-CG");
  }
}
