package com.jzqh.account;

import com.jzqh.SpringContextHolder;
import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Authority;
import com.jzqh.account.accessmanagement.authority.Menu;
import com.jzqh.account.organizestructure.AuthoritiesSetImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "sys_user")
public class UserImpl implements UserDetails, User, Serializable {
    private static final long serialVersionUID = -2426342993719284587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuthoritiesSetImpl> authoritiesSets;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Menu rootMenu;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("o")
    private SortedSet<Action> actions;


    /**
     *
     */
    @Override
    public void registered() {
        // SpringContextHolder.getBean(UserCatalog.class).saveAndFlush(this);
    }

    /**
     *
     */
    @Override
    public void login() {
        //TODO:暂缓实施
    }

    /**
     *
     */
    @Override
    public void authority(UserImpl userImpl, Authority authority) {
        //TODO:
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean validatePassword(String password) {
        PasswordEncoder encoder = SpringContextHolder.getBean(PasswordEncoder.class);
        return encoder.matches(password, encoder.encode(password));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Menu getRootMenu() {
        return rootMenu;
    }

    @Override
    public boolean hasAuthority(String url) {
        return hasMenuAuthority(url) || hasActionAuthority(url);
    }

    private boolean hasMenuAuthority(String url) {
        Menu root = getRootMenu();
        return querySubMenu(url, root);
    }

    private boolean querySubMenu(String url, Menu menu) {
        if (menu == null) return false;
        if (menu.getUrl().equals(url)) return true;
        for (Menu menu1 : menu.getSubMenus()) {
            if (querySubMenu(url, menu1)) return true;
        }
        return false;
    }

    private boolean hasActionAuthority(String url) {
        for (Action action : this.actions) {
            if (action.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Action> getActions(Menu menu) {
        SortedSet<Action> actions = menu.getActions();
        return Collections.unmodifiableCollection(CollectionUtils.disjunction(actions, this.actions));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
