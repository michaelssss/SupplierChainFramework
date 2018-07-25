package com.jzqh.account;

import com.jzqh.utils.PhoneNumber;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_profile")
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String name;
    private int age;
    private String sexual;
    @PhoneNumber
    private String phone;
    private String email;
}
