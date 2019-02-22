package com.zdy.aipc.domain;

public class Product extends AbstractProduct{

    private ITradeStrategy tradeStrategy;

    public Product(String name,String code) throws Exception{
        this.setProductName(name);
        this.setProdCode(name);
    }

    @Override
    public double getLatestPrice() {
        return 0;
    }

    public double getLastPrice() {
        return 0;
    }

    public String getLastTradeDate() {
        return "20100101";
    }

    @Override
    public double getLatestTradeAmount(){
        return this.getTradeStrategy().getTradeAmount();
    }

    public ITradeStrategy getTradeStrategy() {
        return tradeStrategy;
    }

    public void setTradeStrategy(ITradeStrategy tradeStrategy) {
        this.tradeStrategy = tradeStrategy;
    }
}
