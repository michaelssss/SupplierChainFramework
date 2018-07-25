package com.jzqh.account.accessmanagement.authority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Menu必须以大写开头，代表资源
 */
@Entity
@ToString(callSuper = true)
@Table(name = "sys_menu")
@Setter
@Getter
public class Menu extends Authority {

    private static final long serialVersionUID = 2605479017820377676L;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("o")
    private SortedSet<Menu> subMenus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("o")
    private SortedSet<Action> actions;

    public Menu() {
        this.subMenus = new TreeSet<>();
        this.actions = new TreeSet<>();
    }

    @Override
    public String getType() {
        return MENU;
    }

    public void addMenu(Menu menu) {
        this.subMenus.add(menu);
    }
}
