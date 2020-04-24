package com.zdy.aipc.domain;

import org.springframework.stereotype.Component;

@Component
public class ProductTradeParam {
    private int rid;
    private String prodCode;
    private double baseTradeAmount;
    private double baseTradeIndex;
    private double maxChangeRate;
    private int maxPeriodDays;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public double getBaseTradeAmount() {
        return baseTradeAmount;
    }

    public void setBaseTradeAmount(double baseTradeAmount) {
        this.baseTradeAmount = baseTradeAmount;
    }

    public double getBaseTradeIndex() {
        return baseTradeIndex;
    }

    public void setBaseTradeIndex(double baseTradeIndex) {
        this.baseTradeIndex = baseTradeIndex;
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
        this.maxPeriodDays = maxPeriodDays;
    }
}
