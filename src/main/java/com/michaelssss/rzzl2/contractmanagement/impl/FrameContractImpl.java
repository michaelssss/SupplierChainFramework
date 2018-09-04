package com.michaelssss.rzzl2.contractmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.contractmanagement.FrameContract;
import com.michaelssss.rzzl2.contractmanagement.repository.FrameContractRepository;
import com.michaelssss.rzzl2.exception.ExistException;
import com.michaelssss.utils.BusinessCodeGenerator;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:框架合同实体类
 * @Author:tanshaoxing
 * @Date:2018/7/18
 */
@Builder
@Entity
@Data
@Table(name = "frame_contract")
public class FrameContractImpl implements FrameContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String projectName;//项目名称
    private String versionNo;//版本号
    private String contractName;//合同名称
    private String contractNo;//合同编号
    private BigDecimal amtDeposit;//保证金
    private String appointedPurchased;//约定采购物
    private Long electricityTicketDeadline;//电票最长期限
    private String payerAmtDeposit;//保证金支付方
    private String maxPickUpCycle;//最长提货周期
    private String deliveryWay;//物流方式
    private BigDecimal serviceRate;//服务费率
    private String minBillingCycle;//最短计费周期
    private String getPriceChannel;//获取市场价格渠道
    private Date contractSigningDate;//合同签订日期
    private Date contractStartDate;//合同开始日期
    private Date contractEndDate;//合同有效期至
    private String productName;//产品名
    private String productShelfLife;//产品保质期
    private String productPassRate;//上线合格率
    private String productBadRate;//不良率
    private String auditState;//审批状态

    @Override
    public void addFrameContract() {
        FrameContractImpl frameContract = FrameContractImpl.builder().contractName(this.contractName).build();
        Example ex = Example.of(frameContract);
        frameContract = SpringContextHolder.getBean(FrameContractRepository.class).findOne(ex);
        if (frameContract != null) {
            throw new ExistException("框架合同名称已存在");
        }
        SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateFrameContract() {
        SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    }

    @Override
    public void deleteFrameContract() {
        SpringContextHolder.getBean(FrameContractRepository.class).delete(this.id);
    }

    @Override
    public void approveFrameContract() {
        SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    }

    @Override
    public void confirmFrameContract() {
        this.setAuditState(FrameContract.Confirm);
        SpringContextHolder.getBean(FrameContractRepository.class).saveAndFlush(this);
    }

    @Override
    public String getFrameContractNo() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "HT-KJ");
    }
}
