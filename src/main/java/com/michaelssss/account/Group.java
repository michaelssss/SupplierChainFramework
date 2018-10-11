package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Optional;
import java.util.Set;

/**
 * 本集合代表了一个权限组织， 例如，xxx部门，xxxx小组之类的， 可以任意层级细分
 */
public interface Group {

  String getName();

  /** 设立部门名字 */
  void setName(String name);

  /** 获取部门所有员工 */
  @JsonIgnore
  Set<User> getUsers();

  /** 给部门添加员工 */
  void addUser(User user);

  /** 将员工从部门移除 */
  void removeUser(User user);

  /**
   * 现有部门再有角色 故而增加此接口，将部门与角色进行关联
   */
  void addRole(Role role);

  /**
   * 将角色从部门中移除
   */
  void removeRole(Role role);

  /**
   * 获取部门中角色 如果该角色在部门中不存在，则返回一个Optional.isPresent()==false
   */
  Optional<Role> getRole(String roleName);
}
