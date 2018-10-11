package com.michaelssss.account;

/**
 * @author Michaelssss
 * @date 2018/10/11
 */
public interface Role {

  /**
   * 用户是否属于该角色
   */
  boolean isUserExist(User user);

  /**
   * 将用户与角色进行关联
   */
  boolean addUser(User user);

  boolean removeUser(User user);

  /**
   * 获取角色名称
   */
  String getName();

  /**
   * 设置角色名称
   */
  void setName(String name);
}
