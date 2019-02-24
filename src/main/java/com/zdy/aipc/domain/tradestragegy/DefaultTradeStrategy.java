package com.zdy.aipc.domain.tradestragegy;

import com.zdy.aipc.domain.Product.AbstractProduct;
import com.zdy.aipc.domain.Product.TradeableProduct;
import com.zdy.aipc.domain.TradeInfo;
import com.zdy.aipc.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultTradeStrategy implements ITradeStrategy {

    private TradeableProduct product;

    public DefaultTradeStrategy(TradeableProduct prod){
        this.product = prod;
    }

    @Override
    public double getTradeAmount() throws  Exception{
        TradeInfo tradeInfo = product.getTradeInfo();
        if(tradeInfo == null){
            throw new Exception(String.format("未找到历史交易信息"));
        }
        double latestPrice = 0;

        latestPrice = product.getLatestPrice();

        double lastPrice = tradeInfo.getTradeRecord().getLastTradePrice();
        String lastTradeDate = tradeInfo.getTradeRecord().getLastTradeDate();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now = df.format(new Date());

        double baseTradeAmount = tradeInfo.getTradeParameter().getBaseTradeAmount();
        double maxChangeRate = tradeInfo.getTradeParameter().getMaxChangeRate();
        double maxPeriodDays = tradeInfo.getTradeParameter().getMaxPeriodDays();

        double priceChangeRate = (lastPrice - latestPrice)/lastPrice;


        double daysDiff = 0;
        daysDiff = DateUtils.getDaysDiff(lastTradeDate,now);

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

}
