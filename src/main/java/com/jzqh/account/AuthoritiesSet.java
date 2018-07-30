package com.jzqh.account;

public interface AuthoritiesSet {
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
