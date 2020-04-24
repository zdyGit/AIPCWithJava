package com.zdy.aipc.service.productmarket;

import com.zdy.aipc.dao.ProductInfoDao;
import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SZProductMarket extends ProductMarket{

    public HashMap<String,Object> getLatestTradeInfo(ProductInfo productInfo) {

        HashMap<String,Object> tradeData = new HashMap<String,Object>();
        String response = HttpUtils.sendPost(productInfo.getInfoUrl(),"");
        try{
            String[] arr1 =response.split("=");
            String dataContent = arr1[1].substring(1);
            String[] dataList = dataContent.split(",");

            double latestPrice = Double.parseDouble(dataList[1]);
            double curDropRate = Double.parseDouble(dataList[3])*1.0/100;
            tradeData.put("latestPrice",latestPrice);
            tradeData.put("latestDropRate",curDropRate);
        }
        catch (Exception ex){

        }
        return tradeData;
    }
}
