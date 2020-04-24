package com.zdy.aipc.domain;

import org.springframework.stereotype.Component;

//交易参数
@Component
public class TradeParameter {
    private  double baseTradePrice;
    //基础投资金额
    private double baseTradeAmount;
    //最大涨跌幅限制
    private double maxChangeRate;
    //最长时间间隔
    private int maxPeriodDays;

    public double getBaseTradeAmount() {
        return baseTradeAmount;
    }
    public void setBaseTradeAmount(double baseTradeAmount) {
        if(baseTradeAmount < 0 ){
            baseTradeAmount = 0;
        }
        this.baseTradeAmount = baseTradeAmount;
    }
    public double getMaxChangeRate() {
        return maxChangeRate;
    }
    public void setMaxChangeRate(double maxChangeRate) {
        this.maxChangeRate = maxChangeRate;
    }
    public int getMaxPeriodDays() {
        return maxPeriodDays;
    }
    public void setMaxPeriodDays(int maxPeriodDays) {
        if(maxPeriodDays < 0){
            maxPeriodDays = 0;
        }
        this.maxPeriodDays = maxPeriodDays;
    }
    public double getBaseTradePrice() {
        return baseTradePrice;
    }
    public void setBaseTradePrice(double baseTradePrice) {
        this.baseTradePrice = baseTradePrice;
    }
}
