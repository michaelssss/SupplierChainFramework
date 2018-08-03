package com.jzqh.account;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "sys_auth_set")
public class AuthoritiesSetImpl implements AuthoritiesSet, Serializable {

    private static final long serialVersionUID = 5892583758056026304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotEmpty
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AuthoritiesSetImpl parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuthoritiesSetImpl> children = new ArrayList<>();

    /**
     * 因为是根对象的引用修改，jpa是无法侦测到此处被修改的，
     *
     * @param parent
     */
    @Override
    public void addParentAuthSet(AuthoritiesSet parent) {
        this.setParent((AuthoritiesSetImpl) parent);
    }

    @Override
    public void addChildrenAuthSet(AuthoritiesSet children) {
        this.children.add((AuthoritiesSetImpl) children);
        children.addParentAuthSet(this);
    }

    @Override
    public void removeChildren(AuthoritiesSet children) {
        this.children.remove((AuthoritiesSetImpl) children);
        children.addParentAuthSet(null);
    }

    @Override
    public void authority(Authority authority) {
        this.authorities.add(authority);
    }

    @Override
    public void unAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthoritiesSetImpl)) return false;
        AuthoritiesSetImpl that = (AuthoritiesSetImpl) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "AuthoritiesSetImpl{" +
                "name='" + name + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public Set<Authority> getAllAuthority() {
        return Collections.unmodifiableSet(this.authorities);
    }
}
