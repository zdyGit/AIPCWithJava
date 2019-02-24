package com.zdy.aipc.domain;

public class TradeParameter {
    private double baseTradeAmount;
    private double maxChangeRate;
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
}
