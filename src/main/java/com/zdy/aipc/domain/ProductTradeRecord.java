package com.zdy.aipc.domain;

import org.springframework.stereotype.Component;

@Component
public class ProductTradeRecord {
    private int rid;
    private String prodCode;

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    private double tradeAmount;
    private String tradeDate;
    private double tradeIndex;
    private int tradeType;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getTradeIndex() {
        return tradeIndex;
    }

    public void setTradeIndex(double tradeIndex) {
        this.tradeIndex = tradeIndex;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }
}
