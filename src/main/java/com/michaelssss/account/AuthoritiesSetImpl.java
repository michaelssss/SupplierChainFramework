package com.michaelssss.account;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<FunctionName> functionNames = new HashSet<>();

    @Override
    public void authority(FunctionName functionName) {
        this.functionNames.add(functionName);
    }

    @Override
    public void unAuthority(FunctionName functionName) {
        this.functionNames.remove(functionName);
    }

    @Override
    public void setAuthoriesSetname(String name) {
        this.name = name;
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
                ", functionNames=" + functionNames +
                '}';
    }

    @Override
    public Set<FunctionName> getAllAuthority() {
        return Collections.unmodifiableSet(this.functionNames);
    }
}
