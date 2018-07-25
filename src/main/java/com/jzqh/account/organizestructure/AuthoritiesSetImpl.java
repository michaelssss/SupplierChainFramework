package com.jzqh.account.organizestructure;

import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Menu;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@Table(name = "sys_auth_set")
public class AuthoritiesSetImpl implements AuthoritiesSet, Serializable {

    private static final long serialVersionUID = 5892583758056026304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Action> actions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Menu> menus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AuthoritiesSetImpl parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuthoritiesSetImpl> children;

    @Override
    public void authorizeMenu(Menu menu) {
        this.menus.add(menu);
    }

    @Override
    public void authorizeAction(Menu menu, Action action) {
        this.menus.add(menu);
        this.actions.add(action);
    }

    @Override
    public void unAuthorizeMenu(Menu menu) {
        this.menus.remove(menu);
    }

    @Override
    public void unAuthorizeAction(Menu menu, Action action) {
        this.actions.remove(action);
    }
}
