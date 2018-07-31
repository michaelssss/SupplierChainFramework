package com.jzqh.account;

/**
 * 本集合代表了一个权限组织，
 * 例如，xxx部门，xxxx小组之类的，
 * 可以任意层级细分
 */
public interface AuthoritiesSet {

    void addParentAuthSet(AuthoritiesSet parent);

    void addChildrenAuthSet(AuthoritiesSet children);

    void removeChildren(AuthoritiesSet children);

    /**
     * 给予一个权限
     *
     * @param authority
     */
    void authority(Authority authority);

    /**
     * 去除一个权限
     *
     * @param authority
     */
    void unAuthority(Authority authority);
}
