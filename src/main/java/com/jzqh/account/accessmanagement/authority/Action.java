package com.jzqh.account.accessmanagement.authority;

import com.jzqh.utils.ActionUrl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ActionUrl 必须以小写字母开头，代表对资源的操作
 */
@Entity
@Table(name = "sys_action")
@ToString(callSuper = true)
public class Action extends Authority {

    private static final long serialVersionUID = 3055264961715757076L;
    @Setter
    @Getter
    @ActionUrl
    private String url;

    @Override
    public String getType() {
        return ACTION;
    }
}
