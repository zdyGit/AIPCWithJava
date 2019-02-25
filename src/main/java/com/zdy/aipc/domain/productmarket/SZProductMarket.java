package com.zdy.aipc.domain.productmarket;

import com.zdy.aipc.domain.TradeInfo;
import com.zdy.aipc.domain.TradeInfoResp;
import com.zdy.aipc.utils.HttpUtils;

import java.util.HashMap;

public class SZProductMarket extends ProductMarket{


    @Override
    public HashMap<String,Object> getLatestTradeInfo(String prodCode) {

        HashMap<String,Object> tradeData = new HashMap<String,Object>();
        TradeInfo tInfo = TradeInfoResp.getTradeInfo(prodCode);
        String response = HttpUtils.sendPost(tInfo.getInfoUrl(),"");
        try{
            String[] arr1 =response.split("=");
            String dataContent = arr1[1].substring(1);
            String[] dataList = dataContent.split(",");

            double latestPrice = Double.parseDouble(dataList[1]);
            tradeData.put("latestPrice",latestPrice);
        }
        catch (Exception ex){

        }
        return tradeData;
    }
}
