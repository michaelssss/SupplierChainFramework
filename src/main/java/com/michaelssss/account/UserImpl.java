package com.michaelssss.account;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.configuration.ConfigurationCenter;
import com.michaelssss.utils.Sha256;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    private Set<FunctionName> authorities = new HashSet<>();

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
    public void authority(User other, FunctionName functionName) {
        if (hasAuthority(functionName.getFunctionName())) {
            other.authority(functionName);
        } else {
            throw new AuthorityException("you have no Authority");
        }
    }

    @Override
    public void authority(FunctionName functionName) {
        this.authorities.add(functionName);
    }

    @Override
    public Set<FunctionName> getHasAuthorityFunctionName() {
        HashSet<FunctionName> functionNames = new HashSet<>(this.authorities);
        for (AuthoritiesSet authoritiesSet : this.authoritiesSets) {
            functionNames.addAll(authoritiesSet.getAllAuthority());
        }
        return functionNames;
    }

    @Override
    public boolean hasAuthority(String functionUrl) {
        Collection<FunctionName> functionNames = getHasAuthorityFunctionName();
        for (FunctionName functionName : functionNames) {
            if (functionName.getUrl().equals(functionUrl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registered() {
        Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
        this.password = encoder.getPwd(this.password);
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
            token = SpringContextHolder.getBean(TokenCatalog.class).saveAndFlush(token);
            return token;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        TokenCatalog tokenCatalog = SpringContextHolder.getBean(TokenCatalog.class);
        Token sample = new Token();
        sample.setToken(token);
        Token token1 = tokenCatalog.findOne(Example.of(sample));
        if (token1.getUser().uid.equals(this.uid)) {
            tokenCatalog.delete(token1);
        }
    }

    @Override
    public void updatePassword(String password) {
        Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
        this.password = encoder.getPwd(password);
        SpringContextHolder.getBean(UserCatalog.class).saveAndFlush(this);
    }

    @Override
    public UserProfile getProfile() {
        return this.userProfile;
    }
}
