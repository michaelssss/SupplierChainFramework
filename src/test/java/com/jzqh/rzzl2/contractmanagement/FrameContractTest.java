package com.jzqh.rzzl2.contractmanagement;

import com.jzqh.SpringBootTestBasic;
import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.contractmanagement.impl.FrameContractImpl;
import com.jzqh.rzzl2.contractmanagement.repository.FrameContractRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:框架合同测试类
 * @Author:tanshaoxing
 * @Date:2018/7/18
 */
public class FrameContractTest extends SpringBootTestBasic {

    public FrameContract getFrameContract() {
        FrameContract frameContract = FrameContractImpl.builder().amtDeposit(BigDecimal.valueOf(140.00)).appointedPurchased("手机").auditState("1")
                .contractEndDate(new Date()).contractName("测试框架合同").contractStartDate(new Date()).contractNo(FrameContractImpl.builder().build().getFrameContractNo()).contractSigningDate(new Date())
                .deliveryWay("空运").electricityTicketDeadline(3l).getPriceChannel("不知道").maxPickUpCycle("三个月").minBillingCycle("一个月")
                .payerAmtDeposit("线下支付").productBadRate("10").productName("华为p20").productPassRate("90").productShelfLife("36个月").projectName("测试项目")
                .projectId(1l).serviceRate(BigDecimal.valueOf(100)).build();
        return frameContract;
    }

    /**
     * 添加框架合同
     */
    @Test
    public void addFrameContract() {
        FrameContract frameContract = getFrameContract();
        ((FrameContractImpl) frameContract).setContractNo(frameContract.getFrameContractNo());
        frameContract.addFrameContract();
        frameContract.deleteFrameContract();
    }

    /**
     * 修改框架合同
     */
    @Test
    public void updateFrameContract() {
        FrameContract frameContract = getFrameContract();
        frameContract.addFrameContract();
        ((FrameContractImpl) frameContract).setContractName("修改测试框架合同");
        Assert.assertEquals("修改测试框架合同", ((FrameContractImpl) frameContract).getContractName());
        frameContract.updateFrameContract();
        frameContract.deleteFrameContract();
    }

    @Test
    public void deleteFrameContract() {
        FrameContract frameContract = getFrameContract();
        frameContract.addFrameContract();
        frameContract.deleteFrameContract();
        frameContract = SpringContextHolder.getBean(FrameContractRepository.class).findOne(((FrameContractImpl) frameContract).getId());
        Assert.assertNull(frameContract);
    }

    /**
     * 审批
     */
    @Test
    public void approveFrameContract() {
        FrameContract frameContract = getFrameContract();
        frameContract.addFrameContract();
        ((FrameContractImpl) frameContract).setAuditState(FrameContract.Approving);
        frameContract.approveFrameContract();
        frameContract.deleteFrameContract();
    }

    /**
     * 确认生效
     */
    @Test
    public void confirmFrameContract() {
        FrameContract frameContract = getFrameContract();
        frameContract.addFrameContract();
        frameContract.confirmFrameContract();
        Assert.assertEquals(FrameContract.Confirm, ((FrameContractImpl) frameContract).getAuditState());
        frameContract.deleteFrameContract();

    }

}
