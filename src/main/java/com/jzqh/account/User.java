package com.jzqh.account;

import java.util.Set;

public interface User {
    void authority(User other, Authority authority);

    UserProfile getProfile();

    void authority(Authority authority);

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
     * 获取当前用户的菜单列表
     *
     * @return
     */
    Set<Authority> getMenus();

    /**
     * 获取当前用户的权限
     *
     * @return
     */
    Set<Authority> getActions();


    /**
     * 判断用户是否拥有此菜单的权限
     *
     * @param menuUrl 需要判断的URL
     * @return Boolean
     */
    boolean hasAuthority(Authority menuUrl);

    /**
     * 判断用户是否拥有此菜单的权限
     *
     * @param menuUrl 需要判断的URL
     * @return Boolean
     */
    boolean hasAuthority(String menuUrl);

    /**
     * 注册一个用户
     */
    void registered();
}
