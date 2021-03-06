package com.michaelssss.business.contractmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.contractmanagement.Contract;
import com.michaelssss.business.contractmanagement.repository.FrameContractRepository;
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
 * @Description:框架合同实体类 @Author:tanshaoxing @Date:2018/7/18
 */
@Builder
@Entity
@Data
@Table(name = "frame_contract")
public class FrameContractImpl implements Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long projectId;
  private String projectName; // 项目名称
  private String versionNo; // 版本号
  private String contractName; // 合同名称
  private String contractNo; // 合同编号
  private BigDecimal amtDeposit; // 保证金
  private String appointedPurchased; // 约定采购物
  private Long electricityTicketDeadline; // 电票最长期限
  private String payerAmtDeposit; // 保证金支付方
  private String maxPickUpCycle; // 最长提货周期
  private String deliveryWay; // 物流方式
  private BigDecimal serviceRate; // 服务费率
  private String minBillingCycle; // 最短计费周期
  private String getPriceChannel; // 获取市场价格渠道
  private Date contractSigningDate; // 合同签订日期
  private Date contractStartDate; // 合同开始日期
  private Date contractEndDate; // 合同有效期至
  private String productName; // 产品名
  private String productShelfLife; // 产品保质期
  private String productPassRate; // 上线合格率
  private String productBadRate; // 不良率
  private String auditState; // 审批状态

  @Override
  public void add() {
    FrameContractImpl frameContract =
        FrameContractImpl.builder().contractName(this.contractName).build();
    Example<FrameContractImpl> ex = Example.of(frameContract);
    if (SpringContextHolder.getBean(FrameContractRepository.class).exists(ex)) {
      throw new BusinessException("框架合同名称已存在");
    }
    this.contractNo = getFrameContractNo();
    SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
  }

  @Override
  public void update() {
    if (this.getAuditState().equals(EDITABLE)) {
      SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    } else {
      throw new BusinessException("合同已经无法修改");
    }
  }

  @Override
  public void apply() {
    // TODO：发起审批
  }

  @Override
  public void confirm() {
    if (this.auditState.equals(Contract.APPROVED)) {
      this.setAuditState(Contract.CONFIRM);
      SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    } else {
      throw new BusinessException("合同未经过审批或审批不通过");
    }
  }

  private String getFrameContractNo() {
    return SpringContextHolder.getBean(BusinessCodeGenerator.class)
        .getSequence(this.getClass(), "HT-KJ");
  }
}
