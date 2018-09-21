package com.michaelssss.account;

import com.michaelssss.SpringContextHolder;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "sys_group")
public class GroupImpl implements Group, Serializable {

    private static final long serialVersionUID = 5892583758056026304L;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserImpl> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotEmpty
    @ApiModelProperty(value = "部门名称")
    private String name;

    public GroupImpl() {
        this.name = "";
        this.users = new HashSet<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<User> getUsers() {
        return Collections.unmodifiableSet(this.users);
    }

    @Override
    public void addUser(User user) {
        this.users.add((UserImpl) user);
    }

    @Override
    public void removeUser(User user) {
        this.users.removeIf((user1 -> user.getUsername().equals(user1.getUsername())));
        SpringContextHolder.getBean(GroupCatalog.class).saveAndFlush(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupImpl)) return false;
        GroupImpl that = (GroupImpl) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
