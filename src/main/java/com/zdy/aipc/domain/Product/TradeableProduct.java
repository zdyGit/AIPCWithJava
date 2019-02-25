package com.zdy.aipc.domain.Product;

import com.zdy.aipc.domain.TradeInfo;
import com.zdy.aipc.domain.TradeInfoResp;
import com.zdy.aipc.domain.TradeRecord;
import com.zdy.aipc.domain.productconfig.IProductConfig;
import com.zdy.aipc.domain.productmarket.ProductMarket;
import com.zdy.aipc.domain.tradestragegy.*;
import com.zdy.aipc.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TradeableProduct extends AbstractProduct{
    private ITradeStrategy tradeStrategy;
    private ProductMarket productMarket;
    private TradeInfo tradeInfo;
    private IProductConfig productConfig;

    public TradeableProduct(String code) throws Exception{
        this.setProdCode(code);
    }

    public void setProductConfig(IProductConfig pConfig){
        pConfig.setProductMarkey(this);
        pConfig.setTradeStragegy(this);
    }
    public TradeInfo getTradeInfo() {
        TradeInfo tInfo = TradeInfoResp.getTradeInfo(this.getProdCode());
        this.tradeInfo = tInfo;
        return tInfo;
    }
    public void setTradeInfo() throws Exception{
        SimpleDateFormat dfTime = new SimpleDateFormat("HHmmss");
        String curTime = dfTime.format(new Date());
        if (curTime.compareTo("153000")<0){
            throw  new Exception("after 15：30 can do");
        }

        if(this.tradeInfo ==null){
            getTradeInfo();
        }
        TradeInfo tInfo = this.tradeInfo;
        if(tInfo == null){
            throw  new Exception("历史交易记录为空");
        }

        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setLastTradeAmount(this.getLatestTradeAmount());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now =  df.format(new Date());
        tradeRecord.setLastTradeDate(now);
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
        HashMap<String,Object>  tradeData= productMarket.getLatestTradeInfo(this.getProdCode());
        return (double)tradeData.getOrDefault("latestPrice",0);
    }

    public double getLatestTradeAmount() throws Exception{
        if(this.tradeStrategy == null){
            throw new Exception("tradeStrategy not set");
        }
        return this.tradeStrategy.getTradeAmount();
    }

    public double getLatestChangeRate() throws Exception{
        double latestPrice = getLatestPrice();
        double lastTradePrice = this.getTradeInfo().getTradeRecord().getLastTradePrice();
        return (double) Math.round((latestPrice-lastTradePrice)/lastTradePrice*1000)/1000;
    }

    public int getLatestDaysDiff() throws  Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now = df.format(new Date());
        String lastTradeDate = this.getTradeInfo().getTradeRecord().getLastTradeDate();
        return DateUtils.getDaysDiff(lastTradeDate,now);
    }
}
