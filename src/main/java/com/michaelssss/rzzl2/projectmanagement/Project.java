package com.michaelssss.rzzl2.projectmanagement;

/**
 * @author Micha
 * 对项目进行重构，减少非业务描述
 * -------------------------------------
 * @Description:项目信息管理
 * @Author:tsx
 * @Date:2018/7/16
 */
public interface Project {
    String EDITABLE = "EDITABLE";
    String APPROVING = "APPROVING";

    /**
     * 添加项目信息
     */
    void addProject();

    /**
     * 修改项目信息
     */
    void updateProject();

    /**
     * 发起审批
     */
    void apply();
}
