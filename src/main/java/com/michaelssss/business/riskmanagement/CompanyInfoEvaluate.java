package com.michaelssss.business.riskmanagement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "company_evaluate")
@Getter
@Setter
public class CompanyInfoEvaluate extends AbstractEvaluate{
    private final static Map<String,String> map=new HashMap<>();
    static {
        map.put("有限公司","5");
        map.put("个人独资企业","3");
        map.put("个体工商户","1");

        map.put("主营业务持续经营5年（含）以上","10");
        map.put("主营业务持续经营3年（含）-5年","8");
        map.put("主营业务持续经营1年（含）-3年","5");
        map.put("主营业务持续经营1年以下","0");

        map.put("自有产权的经营场所","10");
        map.put("租用的经营场所","5");
        map.put("无经营场所","0");

        map.put("省级以上重点市场或商业区","10");
        map.put("地市级重点市场或商业区","8");
        map.put("一般地市级及以上城镇","6");
        map.put("一般县级城镇","4");
        map.put("一般县级以下城镇","2");
        map.put("农村及其它","0");

        map.put("全资控股","5");
        map.put("股东（实际控制人或持股50%含以上）-1","3");
        map.put("股东（实际控制人或持股50%含以上)-2","1");

        map.put("20人（含）以上","5");
        map.put("10人（含）-20人","4");
        map.put("5人（含）-10人","2");
        map.put("5人以下","1");

        map.put("可提供纳税证明，且年纳税额＞＝10万","5");
        map.put("可提供纳税证明，且年纳税额＞＝5万","4");
        map.put("可提供纳税证明，且年纳税额＞＝2万","3");
        map.put("可提供纳税证明，且年纳税额＜2万","2");
        map.put("无法提供纳税证明","0");

        map.put("营业收入＞＝500万","5");
        map.put("营业收入＞＝100万-1","4");
        map.put("营业收入＞＝50万-1","3");
        map.put("营业收入＜10万","2");
        map.put("无经营收入","0");

        map.put("营业收入＞＝100万-2","5");
        map.put("营业收入＞＝50万-2","4");
        map.put("营业收入＞＝10万","3");
        map.put("营业收入＜0万","2");
        map.put("亏损","0");

        map.put("资产负债率<＝50%","5");
        map.put("资产负债率<＝70%","4");
        map.put("资产负债率<＝100%","3");
        map.put("资产负债率＜=150%","2");
        map.put("资产负债率＜=200%","0");

        map.put("盈利年限>=10年","5");
        map.put("盈利年限>=5年","4");
        map.put("盈利年限>=2年","3");
        map.put("盈利年限>=0年-1","2");
        map.put("盈利年限>=0年-2","0");

        map.put("客户数量>=100家","5");
        map.put("客户数量>=50家","4");
        map.put("客户数量>=10家","3");
        map.put("客户数量>=5家","2");
        map.put("客户数量>=0家","0");

        map.put("天数<=10天","5");
        map.put("天数<=30天","4");
        map.put("天数<=90天","3");
        map.put("天数<=180天","2");
        map.put("天数<=360天","0");

        map.put("集中比例<=30%","5");
        map.put("集中比例<=50%","4");
        map.put("集中比例<=70%","3");
        map.put("集中比例<=90%","2");
        map.put("集中比例<=100%","0");

        map.put("杠杆比例<30%","5");
        map.put("杠杆比例<60%","4");
        map.put("杠杆比例<70%","3");
        map.put("杠杆比例<100%","2");
        map.put("杠杆比例<200%","0");

        map.put("借款额额度比例<10-1","5");
        map.put("借款额额度比例<10-2","4");
        map.put("借款额额度比例<10-3","3");
        map.put("借款额额度比例<10-4","2");
        map.put("借款额额度比例<10-5","0");

        map.put("比例>2-1","5");
        map.put("比例>2-2","4");
        map.put("比例>2-3","3");
        map.put("比例>2-4","2");
        map.put("比例>-5","0");
    }
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
