package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * 本集合代表了一个权限组织，
 * 例如，xxx部门，xxxx小组之类的，
 * 可以任意层级细分
 */
public interface Group {
    /**
     * 设立部门名字
     *
     * @param name
     */
    void setGroupName(String name);

    /**
     * 获取部门所有员工
     *
     * @return
     */
    @JsonIgnore
    Set<User> getUsers();

    /**
     * 给部门添加员工
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 将员工从部门移除
     *
     * @param user
     */
    void removeUser(User user);
}
