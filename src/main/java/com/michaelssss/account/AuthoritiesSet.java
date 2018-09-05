package com.michaelssss.account;

import java.util.Set;

/**
 * 本集合代表了一个权限组织，
 * 例如，xxx部门，xxxx小组之类的，
 * 可以任意层级细分
 */
public interface AuthoritiesSet {

    void setAuthoriesSetname(String name);

    /**
     * 给予一个权限
     *
     * @param functionName
     */
    void authority(FunctionName functionName);

    /**
     * 去除一个权限
     *
     * @param functionName
     */
    void unAuthority(FunctionName functionName);

    Set<FunctionName> getAllAuthority();
}
