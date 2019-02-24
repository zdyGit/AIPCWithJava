package com.zdy.aipc.domain;


public class TradeInfo {
    private String infoUrl;
    private String prodName;
    private String prodCode;
    private TradeParameter tradeParameter;
    private TradeRecord tradeRecord;

    public TradeInfo(String code){
        this.setProdCode(code);
    }

    public String getProdCode() {
        return this.prodCode;
    }
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode.trim().toLowerCase();
    }
    public TradeParameter getTradeParameter() {
        return tradeParameter;
    }
    public void setTradeParameter(TradeParameter tradeParameter) {
        this.tradeParameter = tradeParameter;
    }
    public TradeRecord getTradeRecord() {
        return tradeRecord;
    }
    public void setTradeRecord(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getInfoUrl() {
        return infoUrl;
    }
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }
}
