package com.jzqh.account.organizestructure;

import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Menu;

import java.util.Set;

public interface AuthoritiesSet {
    /**
     * 将一个菜单授予当前权限集
     *
     * @param menu 权限对象
     */
    void authorizeMenu(Menu menu);

    /**
     * 将一个菜单下的动作授权当前权限集
     *
     * @param menu
     * @param action
     */
    void authorizeAction(Menu menu, Action action);

    /**
     * 将一个菜单取消授予当前权限集
     *
     * @param menu 权限对象
     */
    void unAuthorizeMenu(Menu menu);

    /**
     * 将一个菜单下的动作取消授权当前权限集
     *
     * @param menu
     * @param action
     */
    void unAuthorizeAction(Menu menu, Action action);

    /**
     * 返回本授权集合中所有的权限对象
     *
     * @return 权限对象集合
     */
    Set<Menu> getMenus();

    Set<Action> getActions();
}
