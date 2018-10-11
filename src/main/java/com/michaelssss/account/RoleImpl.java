package com.michaelssss.account;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * 本类代表了角色 角色与Group类似 同样没有权限
 *
 * @author Michaelssss
 * @date 2018/10/11
 */
@Data
@Entity
@Table(name = "sys_role")
public class RoleImpl implements Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  @ManyToMany
  private Set<UserImpl> users;

  private String name;

  @Override
  public boolean isUserExist(User user) {
    return users.stream().anyMatch(user1 -> user1.getUsername().equals(user.getUsername()));
  }

  @Override
  public boolean addUser(User user) {
    return users.add((UserImpl) user);
  }

  @Override
  public boolean removeUser(User user) {
    return users.removeIf(user1 -> user1.getUsername().equals(user.getUsername()));
  }
}
