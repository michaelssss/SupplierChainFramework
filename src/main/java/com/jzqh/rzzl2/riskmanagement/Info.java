package com.jzqh.rzzl2.riskmanagement;

import java.util.HashMap;
import java.util.Map;

/**
 * 选项与分数的映射
 * 通过单例模式，使之只创建一次
 */
public class Info {
    private static volatile Info instance;

    private Info(){
    }

    public static Info getInstance(){
        if(instance == null){
            synchronized (Info.class){
                if(instance == null) {
                    instance = new Info();
                }
            }
        }
        return instance;
    }
    public Map map(){
        Map<String, String> map = new HashMap<String, String>();

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
        map.put("股东（实际控制人或持股50%含以上）","3");
        map.put("股东（实际控制人或持股50%含以上）","1");

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
        map.put("营业收入＞＝100万","4");
        map.put("营业收入＞＝50万","3");
        map.put("营业收入＜10万","2");
        map.put("无经营收入","0");

        map.put("营业收入＞＝100万","5");
        map.put("营业收入＞＝50万","4");
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
        map.put("盈利年限>=0年","2");
        map.put("盈利年限>=0年","0");

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

        map.put("借款额额度比例<10","5");
        map.put("借款额额度比例<10","4");
        map.put("借款额额度比例<10","3");
        map.put("借款额额度比例<10","2");
        map.put("借款额额度比例<10","0");

        map.put("比例>2","5");
        map.put("比例>2","4");
        map.put("比例>2","3");
        map.put("比例>2","2");
        map.put("比例>2","0");

        return map;
    }

}
