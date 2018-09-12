package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.SupplierClientApply;

/**
 * @Description:合作伙伴基础信息仓储商信息
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Supplier {

    /**
     * 添加供应方信息
     */
    void apply(SupplierClientApply supplierClientApply, Company company);

    /**
     * 获取公司信息
     *
     * @return 当前绑定的公司信息
     */
    Company getCompany();
}
