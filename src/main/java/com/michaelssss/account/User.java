package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

public interface User {
    void authority(User other, FunctionName functionName);

    UserProfile getProfile();

    void authority(FunctionName functionName);

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
    @JsonIgnore
    Set<FunctionName> getHasAuthorityFunctionName();


    /**
     * 判断用户是否拥有此功能的权限
     *
     * @param functionName 需要判断的功能名称
     * @return Boolean
     */
    boolean hasAuthority(String functionName);

    /**
     * 注册一个用户
     */
    void registered();

    /**
     * 登陆
     *
     * @return 若登陆失败返回null，成功则返回相应的Token
     */
    Token login(String password, Date outdate);

    /**
     * 使传入Token失效
     *
     * @param token 登陆使用Token
     */
    void logout(String token);

    void updatePassword(String password);
}
