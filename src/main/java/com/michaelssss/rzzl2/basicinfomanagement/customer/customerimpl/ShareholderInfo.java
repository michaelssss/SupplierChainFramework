package com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.ShareholderRepository;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shareholder_info")
public class ShareholderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shareholderName;
    private String fundedOfRatio;
    private String contribution;
    private String currency;
    private String investmentOfType;
    private String remark;


    public boolean addInfo() {
        SpringContextHolder.getBean(ShareholderRepository.class).saveAndFlush(this);
        return false;
    }


    public boolean updateInfo() {
        SpringContextHolder.getBean(ShareholderRepository.class).saveAndFlush(this);
        return false;
    }


    public boolean deleteInfo() {
        SpringContextHolder.getBean(ShareholderRepository.class).delete(this.id);
        return false;
    }
}
