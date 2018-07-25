package com.jzqh.rzzl2.projectmanagement;

/**
 * @Description:项目批复管理
 * @Author:tsx
 * @Date:2018/7/16
 */
public interface Approval {
    String Confirm = "Confirm";
    String Terminate = "Terminate";

    /**
     * 添加 项目批复
     */
    void addProjectApprovalInfo();

    /**
     * 修改项目批复信息
     */
    void updateProjectInfo();

    /**
     * 删除项目批复信息
     */
    void deleteProjectInfo();

    /**
     * 项目批复生效
     */
    void confirm();

    /**
     * 生成项目批复编号
     *
     * @return 批复编号
     */
    String getProjectApprovalCode();

}


