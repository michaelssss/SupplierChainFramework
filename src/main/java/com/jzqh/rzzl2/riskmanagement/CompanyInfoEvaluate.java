package com.jzqh.rzzl2.riskmanagement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "company_evaluate")
@Getter
@Setter
public abstract class CompanyInfoEvaluate extends AbstractEvaluate{
    @NotNull
    private String companyNature; // 企业性质

    @NotNull
    private String operatingStability; // 经营稳定性

    @NotNull
    private String operatingPlaceStability; // 经营场所稳定性

    @NotNull
    private String businessRegionalPositioning; // 经营区域定位

    @NotNull
    private String stake; // 控股

    @NotNull
    private String  engagedMenber; // 从业人数

    @NotNull
    private String ratepaying; // 纳税

    @NotNull
    private  String  operatingReceipt; // 营业收入

    @NotNull
    private String grossProfit; // 毛利润

    @NotNull
    private String debtAssetRatio; // 资产负债率

    @NotNull
    private String profitYear;// 盈利年限

    @NotNull
    private String customers; // 贸易上下游客户

    @NotNull
    private String receivableAccountPeriod; // 应收款账期

    @NotNull
    private String buyerConcentration; // 买方集中度

    @NotNull
    private String leverageRatio; // 杠杆比例

    @NotNull
    private String  bankCopy; // 银行对账单比例

    @NotNull
    private String shortTermLiquidity; // 短期偿债能力比例

    @Override
    public BigDecimal eval() {
        Info info = Info.getInstance();
        Map<String,String> map = info.map();
        String[] key = get();
        long score = 0;

        for(int i = 0; i< key.length; i++) {
            for (Map.Entry entry : map.entrySet())
                if (entry.getKey().equals(key[i])) {
                    score += Long.valueOf((String) entry.getValue());
                }
        }
        return BigDecimal.valueOf(score);
    }

    /**
     * 获取每个字段的内容，保存到数组key内
     * @return
     */
    public String[] get(){
        String[] key ={this.companyNature,
                       this.operatingStability,
                       this.operatingPlaceStability,
                       this.businessRegionalPositioning,
                       this.stake,
                       this.engagedMenber,
                       this.ratepaying,
                       this.operatingReceipt,
                       this.grossProfit,
                       this.debtAssetRatio,
                       this.profitYear,
                       this.customers,
                       this.receivableAccountPeriod,
                       this.businessRegionalPositioning,
                       this.leverageRatio,
                       this.bankCopy,
                       this.shortTermLiquidity};

        return key;
    }

}
