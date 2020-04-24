package com.zdy.aipc.service.tradestragegy;

import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.dao.ProductTradeParamDao;
import com.zdy.aipc.dao.ProductTradeRecordDao;
import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.domain.ProductTradeParam;
import com.zdy.aipc.domain.ProductTradeRecord;
import com.zdy.aipc.service.ProductService;
import com.zdy.aipc.utils.DateUtils;
import com.zdy.aipc.utils.LogbackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DefaultTradeStrategy implements ITradeStrategy {

    @Autowired
    private ProductTradeRecordDao productTradeRecordDao;
    @Autowired
    private ProductTradeParamDao productTradeParamDao;

    @Override
    public JSONObject getTradeInfo(ProductInfo product) throws  Exception{
        ProductTradeRecord productTradeRecord = productTradeRecordDao.getLatestProductTradeRecord(product.getProdCode());
        ProductTradeParam productTradeParam = productTradeParamDao.getProductTradeParamByProdCode(product.getProdCode());
        //最新净值
        double latestPrice = product.getLatestPrice();


        //上次交易净值
        double lastPrice = productTradeRecord == null ? latestPrice:productTradeRecord.getTradeIndex();
        //上次交易时间
        String lastTradeDate = productTradeRecord == null?"20000101":productTradeRecord.getTradeDate();

        // 基础交易金额
        double baseTradeAmount = productTradeParam.getBaseTradeAmount();
        //最大涨跌幅
        double maxChangeRate = productTradeParam.getMaxChangeRate();
        //最大交易间隔
        int maxPeriodDays = productTradeParam.getMaxPeriodDays();
        //基础交易标准
        double baseTradePrice = productTradeParam.getBaseTradeIndex();

        // 距上次交易涨幅
        double priceChangeRate = (latestPrice - lastPrice)/lastPrice;

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now = df.format(new Date());
        //距上次交易时间间隔
        int daysDiff = DateUtils.getDaysDiff(lastTradeDate,now);

        JSONObject jres = new JSONObject();
        double tradeAmount = 0 ;
        int tradeType;

        //跌幅超过最大涨跌幅，立即发起交易
        if(priceChangeRate <0 && 0-priceChangeRate >= maxChangeRate){
            tradeType = 1;
            tradeAmount = getTradeAmount(baseTradePrice,latestPrice,baseTradeAmount);
        }
        else{
            //交易时间超过时间间隔，立即发起交易
            if(daysDiff >= maxPeriodDays){
                tradeType = 2;
                tradeAmount = getTradeAmount(baseTradePrice,latestPrice,baseTradeAmount);
            }
            else{
                tradeType = 0;
                tradeAmount = 0;
            }
        }
        jres.put("prodCode",product.getProdCode());
        jres.put("prodName",product.getProdName());
        jres.put("changeRate",String.format("%.3f%%[%.3f%%]",priceChangeRate*100.0,(0-maxChangeRate)*100.0));
        jres.put("daysDiff",String.format("%d days[%d days]",daysDiff,maxPeriodDays));
        jres.put("tradeAmount",tradeAmount);
        jres.put("tradeType",tradeType);
        return jres ;
    }

    private double getTradeAmount(double baseTradePrice,double latestTradePrice,double baseTradeAmount){

        double amount = 0;
        if(latestTradePrice >= baseTradePrice){
            return baseTradeAmount;
        }
        double dropRate = (baseTradePrice - latestTradePrice) / baseTradePrice;
        double rDropRate = (dropRate*6+1);
        amount = baseTradeAmount * (rDropRate * rDropRate * rDropRate);
        LogbackUtils.info(String.format("%f,%f,%f,%f",baseTradePrice,baseTradeAmount,latestTradePrice,amount));
        return amount;
    }

}
