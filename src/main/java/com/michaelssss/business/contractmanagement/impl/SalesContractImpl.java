package com.michaelssss.business.contractmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.contractmanagement.Contract;
import com.michaelssss.business.contractmanagement.repository.SalesContractRepository;
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
 * @Description: 销售合同 @Author:tanshaoxing @Date:2018/7/18
 */
@Builder
@Entity
@Data
@Table(name = "sales_contract")
public class SalesContractImpl implements Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String projectNo; // 项目编号
  private String projectName; // 项目名称
  private Long frameContractId; // 主合同id
  private String frameContractNo; // 主合同编号
  private String frameContractName; // 主合同名称
  private Long salesOrderId; // 销售订单id
  private String salesOrderNo; // 销售订单编号
  private String salesOrderName; // 销售订单名称
  private String salesContractName; // 销售合同名称
  private String salesVersionNo; // 版本号
  private String salesContractNo; // 销售合同编号
  private String transportWay; // 运输方式
  private String deliveryCostBearer; // 运费承担
  private Date deliveryEndDate; // 提货截止日期
  private BigDecimal amtTotal; // 合计
  private String amtBlockTotal; // 合计大写
  private String currency; // 币种
  private Date effectiveTime; // 合同生效时间
  private Date signTime; // 合同签署时间
  private Date invalidTime; // 合同截止时间
  private String auditState; // 审批状态

  @Override
  public void add() {
    SalesContractImpl frameContract =
        SalesContractImpl.builder().salesContractName(this.salesContractName).build();
    if (SpringContextHolder.getBean(SalesContractRepository.class)
        .exists(Example.of(frameContract))) {
      throw new BusinessException("合同名称已存在");
    }
    this.frameContractNo = getSalesContract();
    SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
  }

  @Override
  public void update() {
    if (!this.auditState.equals(EDITABLE)) {
      throw new BusinessException("合同不可修改状态");
    }
    SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
  }

  @Override
  public void apply() {
    // TODO:发起销售合同审批
  }

  @Override
  public void confirm() {
    this.setAuditState(Contract.CONFIRM);
    SpringContextHolder.getBean(SalesContractRepository.class).saveAndFlush(this);
  }

  private String getSalesContract() {
    return SpringContextHolder.getBean(BusinessCodeGenerator.class)
        .getSequence(this.getClass(), "HT-XS");
  }
}
