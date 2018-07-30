package com.jzqh.account;

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
    private Set<Authority> authorities;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AuthoritiesSetImpl parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuthoritiesSetImpl> children;

    @Override
    public void authority(Authority authority) {
        this.authorities.add(authority);
    }

    @Override
    public void unAuthority(Authority authority) {
        this.authorities.remove(authority);
    }
}
