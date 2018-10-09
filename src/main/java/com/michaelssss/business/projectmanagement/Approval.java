package com.michaelssss.business.projectmanagement;

/**
 * @Description:项目批复管理 @Author:tsx @Date:2018/7/16
 */
public interface Approval {

  String EDITABLE = "EDITABLE";
  String CONFIRM = "CONFIRM";
  String TERMINATE = "TERMINATE";

  /**
   * 添加 项目批复
   */
  void addApproval();

  /**
   * 修改项目批复信息
   */
  void updateApproval();

  /**
   * 项目批复生效
   */
  void confirm();
}
