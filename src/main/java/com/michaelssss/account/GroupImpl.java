package com.michaelssss.account;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "sys_group")
public class GroupImpl implements Group, Serializable {

  private static final long serialVersionUID = 5892583758056026304L;

  @ManyToMany
  private Set<UserImpl> users;

  @ManyToMany
  private Set<RoleImpl> roles;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  @ApiModelProperty(value = "部门名称")
  private String name;

  @Override
  public void addRole(Role role) {
    roles.add((RoleImpl) role);
  }

  @Override
  public void removeRole(Role role1) {
    roles.removeIf(role -> role.getName().equals(role1.getName()));
  }

  @Override
  public Optional<Role> getRole(String roleName) {
    return Optional.of(
        roles.stream().filter(role -> role.getName().equals(roleName)).findFirst().get());
  }

  public GroupImpl() {
    this.name = "";
    this.users = new HashSet<>();
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Set<User> getUsers() {
    return Collections.unmodifiableSet(this.users);
  }

  @Override
  public void addUser(User user) {
    this.users.add((UserImpl) user);
  }

  @Override
  public void removeUser(User user) {
    this.users.removeIf((user1 -> user.getUsername().equals(user1.getUsername())));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GroupImpl)) {
      return false;
    }
    GroupImpl that = (GroupImpl) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
