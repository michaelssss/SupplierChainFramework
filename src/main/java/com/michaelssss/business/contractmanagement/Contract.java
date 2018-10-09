package com.michaelssss.business.contractmanagement;

public interface Contract {

  String EDITABLE = "EDITABLE";
  String APPROVED = "APPROVED";
  String APPROVING = "APPROVING";
  String APPROVE_NOT_PASS = "APPROVE_NOT_PASS";
  String CONFIRM = "CONFIRM";
  String TERMINATE = "TERMINATE";

  /**
   * 添加合同
   */
  void add();

  /**
   * 修改合同 exception：不能修改异常
   */
  void update();

  /**
   * 发起合同审批
   */
  void apply();

  /**
   * 合同生效
   */
  void confirm();
}
