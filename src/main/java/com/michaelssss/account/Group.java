package com.michaelssss.account;

import java.util.Set;

/**
 * 本集合代表了一个权限组织，
 * 例如，xxx部门，xxxx小组之类的，
 * 可以任意层级细分
 */
public interface Group {

    void setGroupName(String name);

    Set<User> getUsers();

    void addUser(User user);
}
