package com.zdy.aipc.domain;

import org.springframework.stereotype.Component;

//交易记录
@Component
public class TradeRecord {
    //上次交易单价
    private double lastTradePrice;
    //上次交易日期
    private String lastTradeDate;
    //上次交易金额
    private double lastTradeAmount;

    public double getLastTradePrice() {
        return lastTradePrice;
    }
    public void setLastTradePrice(double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }
    public String getLastTradeDate() {
        return lastTradeDate;
    }
    public void setLastTradeDate(String lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }
    public double getLastTradeAmount() {
        return lastTradeAmount;
    }
    public void setLastTradeAmount(double lastTradeAmount) {
        this.lastTradeAmount = lastTradeAmount;
    }
}
