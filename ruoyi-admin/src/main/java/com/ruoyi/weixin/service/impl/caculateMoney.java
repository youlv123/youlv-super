package com.ruoyi.weixin.service.impl;

import java.math.BigDecimal;

public class caculateMoney {
    //月薪
    private static final String salary="17000";
    //发多少月工资
    private static final String month="12";
    //社保基数
    private static final String socialSecurity="7310";
    //公积金基数
    private static final String providentFund="2590";
    //每月租房扣除
    private static final String rentDeductions="1500";
    //每月赡养老人扣除
    private static final String supportDeductions="0";
    //让公司帮忙缴纳
    private static final String ddd="2000";


    public static void main(String[] args) {
        BigDecimal income = caculteLate();
        BigDecimal tax = caculteTax(income);
        BigDecimal bigDecimal = caculteBefore();
        BigDecimal tax1 = caculteTax(bigDecimal);
        System.out.println("调整公积金后应交税额"+tax+"元");
        System.out.println("调整公积金前应交税额"+tax1+"元");
        System.out.println("让公司帮忙缴纳"+ddd+"元公积金情况下，减少税"+tax1.subtract(tax)+"元");

    }

    public static BigDecimal caculteTax(BigDecimal income){
        BigDecimal tax = BigDecimal.ZERO;
        if (income.compareTo(BigDecimal.valueOf(36000)) <= 0) {
            tax = income.multiply(BigDecimal.valueOf(0.03));
        } else if (income.compareTo(BigDecimal.valueOf(36000)) > 0 && income.compareTo(BigDecimal.valueOf(144000)) <= 0) {
            tax = BigDecimal.valueOf(1080).add(income.subtract(BigDecimal.valueOf(36000)).multiply(BigDecimal.valueOf(0.10)));
        } else if (income.compareTo(BigDecimal.valueOf(144000)) > 0 && income.compareTo(BigDecimal.valueOf(300000)) <= 0) {
            tax = BigDecimal.valueOf(1080).add(BigDecimal.valueOf(10800)).add(income.subtract(BigDecimal.valueOf(144000)).multiply(BigDecimal.valueOf(0.20)));
        } else if (income.compareTo(BigDecimal.valueOf(300000)) > 0 && income.compareTo(BigDecimal.valueOf(420000)) <= 0) {
            tax = BigDecimal.valueOf(1080).add(BigDecimal.valueOf(10800)).add(BigDecimal.valueOf(31200)).add(income.subtract(BigDecimal.valueOf(300000)).multiply(BigDecimal.valueOf(0.25)));
        } else if (income.compareTo(BigDecimal.valueOf(420000)) > 0 && income.compareTo(BigDecimal.valueOf(660000)) <= 0) {
            tax = BigDecimal.valueOf(1080).add(BigDecimal.valueOf(10800)).add(BigDecimal.valueOf(31200)).add(BigDecimal.valueOf(30000)).add(income.subtract(BigDecimal.valueOf(420000)).multiply(BigDecimal.valueOf(0.30)));
        } else if (income.compareTo(BigDecimal.valueOf(660000)) > 0 && income.compareTo(BigDecimal.valueOf(960000)) <= 0) {
            tax = BigDecimal.valueOf(1080).add(BigDecimal.valueOf(10800)).add(BigDecimal.valueOf(31200)).add(BigDecimal.valueOf(30000)).add(BigDecimal.valueOf(60000)).add(income.subtract(BigDecimal.valueOf(660000)).multiply(BigDecimal.valueOf(0.35)));
        } else {
            tax = BigDecimal.valueOf(1080).add(BigDecimal.valueOf(10800)).add(BigDecimal.valueOf(31200)).add(BigDecimal.valueOf(30000)).add(BigDecimal.valueOf(60000)).add(BigDecimal.valueOf(120000)).add(income.subtract(BigDecimal.valueOf(960000)).multiply(BigDecimal.valueOf(0.45)));
        }
        return tax;
    }

    /**
     * 调整公积金之后
     * @return
     */
    public static BigDecimal caculteLate(){
        BigDecimal subtract = new BigDecimal(salary).subtract(new BigDecimal(ddd).multiply(new BigDecimal("2")));
        System.out.println("每月实际工资"+subtract+"元");
        //年度总收入
        BigDecimal total = subtract.multiply(new BigDecimal(month));
        System.out.println("年度总收入"+total+"元");
        //租房减除
        BigDecimal rent = new BigDecimal(rentDeductions).multiply(new BigDecimal("12"));
        System.out.println("租房减除"+rent+"元");
        //赡养老人减除
        BigDecimal old = new BigDecimal(supportDeductions).multiply(new BigDecimal("12"));
        System.out.println("赡养老人减除"+old+"元");
        BigDecimal pensions = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.08")).multiply(new BigDecimal("12"));
        System.out.println("养老保险减除"+pensions+"元");
        BigDecimal medicalInsurance = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.02")).multiply(new BigDecimal("12"));
        System.out.println("医疗保险减除"+medicalInsurance+"元");
        BigDecimal unemployment = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.005")).multiply(new BigDecimal("12"));
        System.out.println("失业保险减除"+unemployment+"元");
        //社保附加减除
        BigDecimal add = pensions.add(medicalInsurance).add(unemployment);
        System.out.println("社保附加减除"+add+"元");
        //住房公积金个人缴纳
        BigDecimal providentFundTotal = new BigDecimal(providentFund).multiply(new BigDecimal("0.12")).multiply(new BigDecimal("12"));
        System.out.println("住房公积金个人缴纳"+providentFundTotal+"元");
        BigDecimal providentFundTotal1 = new BigDecimal(ddd).multiply(new BigDecimal("12"));
        System.out.println("住房公积金个人额外缴纳"+providentFundTotal1+"元");
        System.out.println("每月实际工资发放税前"+subtract.subtract(new BigDecimal(socialSecurity).multiply(new BigDecimal("0.105"))).subtract(
                new BigDecimal(providentFund).multiply(new BigDecimal("0.12"))
        )+"元");
        //应缴纳税基数
        BigDecimal income = total.subtract(new BigDecimal("60000")).subtract(rent).subtract(old).subtract(add).subtract(providentFundTotal)
                .subtract(providentFundTotal1);
        System.out.println("让公司帮忙缴纳"+ddd+"元情况下，自己每月公积金增加"+new BigDecimal(ddd).multiply(new BigDecimal("2"))+"元,"+"应缴纳税基数"+income+"元="+"年度总收入"+total+"元-固定60000减免-租房减除"+rent+"元-赡养老人减除"+old+"元"+
                "-社保附加减除"+add+"元-住房公积金个人缴纳"+providentFundTotal+"元-住房公积金个人额外缴纳"+providentFundTotal1+"元"
        );
        return income;
    }
    /**
     * 调整公积金之前
     * @return
     */
    public static BigDecimal caculteBefore(){

        System.out.println("每月实际工资"+salary+"元");
        //年度总收入
        BigDecimal total = new BigDecimal(salary).multiply(new BigDecimal(month));
        System.out.println("年度总收入"+total+"元");
        //租房减除
        BigDecimal rent = new BigDecimal(rentDeductions).multiply(new BigDecimal("12"));
        System.out.println("租房减除"+rent+"元");
        //赡养老人减除
        BigDecimal old = new BigDecimal(supportDeductions).multiply(new BigDecimal("12"));
        System.out.println("赡养老人减除"+old+"元");
        BigDecimal pensions = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.08")).multiply(new BigDecimal("12"));
        System.out.println("养老保险减除"+pensions+"元");
        BigDecimal medicalInsurance = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.02")).multiply(new BigDecimal("12"));
        System.out.println("医疗保险减除"+medicalInsurance+"元");
        BigDecimal unemployment = new BigDecimal(socialSecurity).multiply(new BigDecimal("0.005")).multiply(new BigDecimal("12"));
        System.out.println("失业保险减除"+unemployment+"元");
        //社保附加减除
        BigDecimal add = pensions.add(medicalInsurance).add(unemployment);
        System.out.println("社保附加减除"+add+"元");
        //住房公积金个人缴纳
        BigDecimal providentFundTotal = new BigDecimal(providentFund).multiply(new BigDecimal("0.12")).multiply(new BigDecimal("12"));
        System.out.println("住房公积金个人缴纳"+providentFundTotal+"元");
        System.out.println("每月实际工资发放税前"+new BigDecimal(salary).subtract(new BigDecimal(socialSecurity).multiply(new BigDecimal("0.105"))).subtract(
                new BigDecimal(providentFund).multiply(new BigDecimal("0.12"))
        )+"元");
        //应缴纳税基数
        BigDecimal income = total.subtract(new BigDecimal("60000")).subtract(rent).subtract(old).subtract(add).subtract(providentFundTotal);
        System.out.println("应缴纳税基数"+income+"元="+"年度总收入"+total+"元-固定60000减免-租房减除"+rent+"元-赡养老人减除"+old+"元"+
                "-社保附加减除"+add+"元-住房公积金个人缴纳"+providentFundTotal+"元"
        );
        return income;
    }
}
