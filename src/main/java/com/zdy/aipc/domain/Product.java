package com.zdy.aipc.domain;

import com.zdy.aipc.domain.productmarket.ProductMarket;
import com.zdy.aipc.domain.tradestragegy.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Product extends AbstractProduct{
    private ITradeStrategy tradeStrategy;
    private ProductMarket productMarket;
    private TradeInfo tradeInfo;

    public Product(String code) throws Exception{
        this.setProdCode(code);
    }

    @Override
    public TradeInfo getTradeInfo() {
        TradeInfo tInfo = TradeInfoResp.getTradeInfo(this.getProdCode());
        this.tradeInfo = tInfo;
        return tInfo;
    }
    public void setTradeInfo() throws Exception{
        TradeInfo tInfo = this.tradeInfo;
        if(tInfo == null){
            throw  new Exception("历史交易记录为空");
        }

        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setLastTradeAmount(this.getLatestTradeAmount());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
        String curDateTime =  df.format(new Date());
        String curDate = curDateTime.split(" ")[0];
        tradeRecord.setLastTradeDate(curDate);
        tradeRecord.setLastTradePrice(this.getLatestPrice());

        tInfo.setTradeRecord(tradeRecord);
        TradeInfoResp.addTradeInfo(tInfo);
    }
    public void setTradeStrategy(ITradeStrategy tradeStrategy) {
        this.tradeStrategy = tradeStrategy;
    }
    public void setProductMarket(ProductMarket productMarket){
        this.productMarket = productMarket;
    }

    @Override
    public double getLatestPrice()  throws  Exception{
        if(productMarket == null){
            throw new Exception("productMarket not set") ;
        }
        HashMap<String,Object>  tradeData= productMarket.getLatestTradeInfo();
        return (double)tradeData.getOrDefault("latestPrice",0);
    }
    @Override
    public double getLatestTradeAmount() throws Exception{
        if(this.tradeStrategy == null){
            throw new Exception("tradeStrategy not set");
        }
        return this.tradeStrategy.getTradeAmount();
    }
    @Override
    public double getLatestChangeRate() throws Exception{
        if(this.tradeStrategy == null){
            throw new Exception("tradeStrategy not set");
        }
        return this.tradeStrategy.getChangeRate();
    }

    @Override
    public int getLatestDaysDiff() throws  Exception{
        if(this.tradeStrategy == null){
            throw new Exception("tradeStrategy not set");
        }
        return this.tradeStrategy.getDaysDiff();
    }
}
