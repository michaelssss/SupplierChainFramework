package com.michaelssss.account;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.configuration.ConfigurationCenter;
import com.michaelssss.utils.Sha256;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "sys_user", indexes = {@Index(name = "idx_username", columnList = "username", unique = true)})
public class UserImpl implements User, Serializable {
    private static final long serialVersionUID = -2426342993719284587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 64)
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuthoritiesSetImpl> authoritiesSets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value = "`order` ASC")
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean validatePassword(String password) {
        Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
        return encoder.isPwd(this.password, password);
    }


    @Override
    public void authority(User other, Authority authority) {
        if (hasAuthority(authority)) {
            other.authority(authority);
        }
    }

    @Override
    public void authority(Authority authority) {
        this.authorities.add(authority);
    }

    @Override
    public Set<Authority> getMenus() {
        HashSet<Authority> authorities = new HashSet<>();
        for (Authority a : this.authorities) {
            if (a.isMenu()) {
                authorities.add(a);
            }
        }
        return authorities;
    }

    @Override
    public Set<Authority> getActions() {
        HashSet<Authority> authorities = new HashSet<>();
        for (Authority a : this.authorities) {
            if (a.isAction()) {
                authorities.add(a);
            }
        }
        return authorities;
    }

    @Override
    public boolean hasAuthority(Authority authority) {
        return this.hasAuthority(authority.getPath());
    }

    @Override
    public boolean hasAuthority(String menuUrl) {
        Set<Authority> set = new HashSet<>();
        for (AuthoritiesSet authoritiesSet : this.authoritiesSets) {
            set.addAll(authoritiesSet.getAllAuthority());
        }
        for (Authority authority : this.authorities) {
            set.add(authority);
        }
        for (Authority authority : set) {
            if (authority.getPath().equals(menuUrl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registered() {
        if (StringUtils.isNotEmpty(this.username) && StringUtils.isNotEmpty(this.password)) {
            SpringContextHolder.getBean(UserCatalog.class).saveAndFlush(this);
        }
    }

    @Override
    public Token login(String password, Date outdate) {
        if (validatePassword(password)) {
            Token token = new Token();
            token.setToken(UUID.randomUUID().toString());
            token.setOutdate(outdate);
            token.setUser(this);
            token = SpringContextHolder.getBean(TokenCatalog.class).save(token);
            return token;
        }
        return null;
    }

    @Override
    public UserProfile getProfile() {
        return this.userProfile;
    }
}
