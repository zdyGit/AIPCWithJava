package com.zdy.aipc.domain;

public class TradeRecord {
    private double lastTradePrice;
    private String lastTradeDate;
    private double lastTradeAmount;

    public TradeRecord(){

    }

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
