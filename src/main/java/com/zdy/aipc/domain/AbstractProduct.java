package com.zdy.aipc.domain;

public class AbstractProduct {
    private String productName;
    private String prodCode;
    private TradeInfo tradeInfo;

    public TradeInfo getTradeInfo() {
        return tradeInfo;
    }
    public void setTradeInfo(TradeInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) throws Exception{
        productName = productName.trim();
        if (productName.length() == 0){
            throw new Exception("参数不能为空");
        }
        this.productName = productName;
    }
    public String getProdCode() {
        return prodCode;
    }
    public void setProdCode(String prodCode) throws Exception{
        prodCode = prodCode.trim();
        if(prodCode.length() == 0){
            throw new Exception("参数不能为空");
        }
        this.prodCode = prodCode.toLowerCase();
    }

    public double getLatestPrice() throws  Exception{
        return 0.00;
    }
    public double getLatestTradeAmount() throws Exception{ return 0.00; };
    public double getLatestChangeRate() throws Exception{
        return 0;
    }
    public int getLatestDaysDiff() throws Exception{
        return 0;
    }
}
