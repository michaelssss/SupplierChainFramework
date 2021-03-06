package com.michaelssss.business.basicinfomanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_contact")
public class Contact implements Serializable {

  private static final long serialVersionUID = -79966342735903733L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long id;

  @ApiModelProperty(value = "联系人类型", example = "唯一联系人")
  private String contactType;

  @ApiModelProperty(value = "联系人姓名", example = "Michaelssss")
  private String name;

  @ApiModelProperty(value = "手机号码", example = "13800000000")
  private String mobilePhone;

  @ApiModelProperty(value = "固话", example = "028-12345678")
  private String phone;

  @ApiModelProperty(value = "传真", example = "")
  private String fax;

  @ApiModelProperty(value = "邮件", example = "test@test.com")
  private String email;

  @ApiModelProperty(value = "联系人所属部门", example = "挖矿部")
  private String department;

  @ApiModelProperty(value = "职位", example = "CEO")
  private String position;

  @ApiModelProperty(value = "备注", example = "")
  private String remark;

  @ApiModelProperty(value = "是否默认", allowableValues = "true,false")
  private String isDefault;
}
