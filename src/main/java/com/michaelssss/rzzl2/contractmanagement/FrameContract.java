package com.michaelssss.rzzl2.contractmanagement;

/**
 * 框架合同管理接口
 */
public interface FrameContract {
    String APPROVED = "APPROVED";
    String APPROVING = "APPROVING";
    String APPROVE_NOT_PASS = "APPROVE_NOT_PASS";
    String CONFIRM = "CONFIRM";
    String TERMINATE = "TERMINATE";

    /***
     * 添加框架合同
     *
     */
    void addFrameContract();

    /**
     * 修改框架合同
     * exception：不能修改异常
     */
    void updateFrameContract();

    /**
     * 合同审批
     */
    void apply();

    /**
     * 合同生效
     */
    void confirmFrameContract();

    /**
     * 获取合同编号
     *
     * @return
     */
    String getFrameContractNo();
}
