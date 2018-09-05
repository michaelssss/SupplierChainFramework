package com.michaelssss.rzzl2.projectmanagement;

/**
 * @Description:项目信息管理
 * @Author:tsx
 * @Date:2018/7/16
 */
public interface Project {
    String Approving = "Approving";

    /**
     * 添加项目信息
     */
    void addProjectInfo();

    /**
     * 修改项目信息
     */
    void updateProjectInfo();

    /**
     * 删除项目信息
     */
    void deleteProjectInfo();

    /**
     * 发起审批
     */
    void permit();
}
