package com.michaelssss.rzzl2.contractmanagement;

/**
 * 框架合同管理接口
 */
public interface FrameContract {
    String Approve_Pass = "Approved";
    String Approving = "Approving";
    String Approve_Not_Pass = "Approve_Not_Pass";
    String Confirm = "Confirm";
    String Terminate = "Terminate";

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

    /***
     *删除框架合同
     */
    void deleteFrameContract();

    /**
     * 合同审批
     */
    void approveFrameContract();

    /**
     * 合同生效
     */
    void confirmFrameContract();

    String getFrameContractNo();
}
