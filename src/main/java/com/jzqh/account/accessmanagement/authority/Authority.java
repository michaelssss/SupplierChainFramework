package com.jzqh.account.accessmanagement.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 权限分为两种：操作，菜单
 * 权限必然是有序的，可以排列成树状结构
 * 操作：ActionUrl
 * 菜单：Menu
 *
 * @see Action
 * @see Menu
 */
@Entity
@Table(name = "sys_auth")
@Data
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Authority implements Serializable, GrantedAuthority, Comparable<Authority> {
    public static final String ACTION = "Action";
    public static final String MENU = "Menu";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    @JsonIgnore
    private int o;

    private String url;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.url;
    }

    public int getOrder() {
        return this.o;
    }

    public void setOrder(int o) {
        this.o = o;
    }

    public abstract String getType();

    @Override
    public int compareTo(Authority o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority = (Authority) o;
        return Objects.equals(getUrl(), authority.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }
}
