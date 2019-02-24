package com.zdy.aipc.domain.tradestragegy;

import com.zdy.aipc.domain.AbstractProduct;
import com.zdy.aipc.domain.TradeInfo;
import com.zdy.aipc.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultTradeStrategy implements ITradeStrategy {

    private AbstractProduct product;

    public DefaultTradeStrategy(AbstractProduct prod){
        this.product = prod;
    }

    @Override
    public double getTradeAmount() throws  Exception{
        TradeInfo tradeInfo = product.getTradeInfo();
        if(tradeInfo == null){
            throw new Exception(String.format("为找到历史交易信息"));
        }
        double latestPrice = 0;

        latestPrice = product.getLatestPrice();

        double lastPrice = tradeInfo.getTradeRecord().getLastTradePrice();
        String lastTradeDate = tradeInfo.getTradeRecord().getLastTradeDate();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
        String curDateTime =  df.format(new Date());
        String curDate = curDateTime.split(" ")[0];
        String curTime = curDateTime.split(" ")[1];

        double baseTradeAmount = tradeInfo.getTradeParameter().getBaseTradeAmount();
        double maxChangeRate = tradeInfo.getTradeParameter().getMaxChangeRate();
        double maxPeriodDays = tradeInfo.getTradeParameter().getMaxPeriodDays();

        double priceChangeRate = (lastPrice - latestPrice)/lastPrice;


        double daysDiff = 0;
        try {
            daysDiff = DateUtils.getDaysDiff(lastTradeDate,curDate);
        }
        catch (Exception ex){
            daysDiff = -1;
        }
        double tradeAmount = 0 ;
        if(priceChangeRate >= maxChangeRate){
            tradeAmount = baseTradeAmount*100*priceChangeRate;
        }
        else{
            if(daysDiff >= maxPeriodDays){
                tradeAmount = baseTradeAmount;
            }
            else{
                tradeAmount = 0;
            }
        }
        return (double) Math.round(tradeAmount * 100) / 100;
    }

    @Override
    public double getChangeRate() throws Exception{
        TradeInfo tradeInfo = product.getTradeInfo();
        double latestPrice = product.getLatestPrice();
        double lastPrice = tradeInfo.getTradeRecord().getLastTradePrice();
        double priceChangeRate = (lastPrice - latestPrice)/lastPrice;
        return priceChangeRate;
    }

    @Override
    public int getDaysDiff(){
        TradeInfo tradeInfo = product.getTradeInfo();
        String lastTradeDate = tradeInfo.getTradeRecord().getLastTradeDate();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
        String curDateTime =  df.format(new Date());
        String curDate = curDateTime.split(" ")[0];
        String curTime = curDateTime.split(" ")[1];
        int daysDiff = 0;
        try {
            daysDiff = DateUtils.getDaysDiff(lastTradeDate,curDate);
        }
        catch (Exception ex){
            daysDiff = -1;
        }
        return  daysDiff;
    }
}
