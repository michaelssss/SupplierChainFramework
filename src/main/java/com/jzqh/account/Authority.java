package com.jzqh.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * 权限分为两种：操作，菜单
 * 权限必然是有序的，可以排列成树状结构
 * 操作：ActionUrl
 * 菜单：Menu
 */
@Entity
@Table(name = "sys_auth")
@Data
@ToString
public class Authority implements Comparable<Authority> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long uid;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "`order`")
    private int order;
    private String name;
    private String path;
    private String redirect;
    private String component;
    private String icon;
    private String title;

    @Override
    public int compareTo(Authority o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority = (Authority) o;
        return Objects.equals(path, authority.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
