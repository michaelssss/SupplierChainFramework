package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sys_profile")
@Data
public class UserProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long uid;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "年龄")
  private String age;

  @ApiModelProperty(value = "性别")
  private String sexual;

  @ApiModelProperty(value = "年龄")
  private String phone;

  @ApiModelProperty(value = "电子邮箱")
  private String email;

  @ApiModelProperty(value = "头像")
  private byte[] headPic;
}
