package com.jzqh.rzzl2.creditmanagement;

import com.jzqh.SpringContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
public class CreditServiceImpl implements CreditService {

    @Override
    public Credit getCompanyCredit(String companyFullName) {

        //1. 建立Example
        //2. 学会用Hibernate查询
        //3. 返回查询结果
        CreditImpl creditImpl = CreditImpl.builder().companyFullName(companyFullName).build();

        Example<CreditImpl> creditExample = Example.of(creditImpl);

        CreditImpl credit = SpringContextHolder.getBean(CreditRepository.class).findOne(creditExample);

        return credit;


    }
}
