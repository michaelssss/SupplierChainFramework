package com.jzqh.rzzl2.creditmanagement;

import com.jzqh.SpringBootTestBasic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/18 13:29
 */
public class CreditTest extends SpringBootTestBasic {

    @Autowired
    private CreditService creditService;
    @Autowired
    private CreditRepository creditRepository;

    /**
     * 插入一条数据
     * 插入成功后
     * 对数据进行查询
     */
    @Test
    public void testGetCompanyCredit() {
        CreditImpl credit = CreditImpl.builder().companyFullName("测试").uid(1L).auditReplyCode("0")
                .type("华").useLimit(BigDecimal.ZERO).currency("now").status("true").duration(0)
                .startDate(new Date()).lastModificationDate(new Date()).remark("flag").build();

        creditRepository.saveAndFlush(credit);

        //查询数据
        Credit credit1 = creditService.getCompanyCredit("测试");

        creditRepository.delete(credit);

    }
}