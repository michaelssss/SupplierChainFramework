package com.jzqh.account;

import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Authority;
import com.jzqh.account.accessmanagement.authority.Menu;

import java.util.Collection;

public interface User {
    /**
     * 用户注册
     */
    void registered();

    /**
     * 用户登陆
     */
    void login();

    /**
     * 给予其他用户授权
     * 注意：仅能给予其他用户本用户已有的权限
     *
     * @param userImpl  其他用户
     * @param authority 权限
     */
    void authority(UserImpl userImpl, Authority authority);

    /**
     * @return 用户名
     */
    String getUsername();

    /**
     * 验证密码
     *
     * @param password 密码，明文
     * @return 密码验证成功或者失败
     */
    boolean validatePassword(String password);

    /**
     * 获取当前用户的根菜单
     *
     * @return
     */
    Menu getRootMenu();

    /**
     * 获取当前用户在此菜单下的权限
     *
     * @param menu 需要查询的菜单
     * @return
     */
    Collection<Action> getActions(Menu menu);

    /**
     * 判断用户是否拥有此菜单的权限
     *
     * @param menuUrl 需要判断的URL
     * @return Boolean
     */
    boolean hasAuthority(String menuUrl);
}
