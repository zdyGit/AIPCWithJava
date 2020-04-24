package com.zdy.aipc.scheduler;

import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.service.ProductService;
import com.zdy.aipc.utils.LogbackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ProductScheduler {
    private static final String cronStr = "0 0 17 * * ?";

    @Autowired
    private ProductService productService;

    @Scheduled(cron=cronStr)
    public void autoUpdateBaseindex() throws Exception{
        List<ProductInfo> productInfoList =  productService.getProductList();
        for(int i=0;i< productInfoList.size();i++){
            updateBaseindex(productInfoList.get(i).getProdCode());
        }
    }

    @Scheduled(cron="0 0 16 * * ?")
    public void addTradeRecord() throws Exception{
        List<ProductInfo> productInfoList =  productService.getProductList();
        for(int i=0;i< productInfoList.size();i++){
            addTradeNewRecord(productInfoList.get(i).getProdCode());
        }
    }

    private void updateBaseindex(String prodCode) throws Exception{
        HashMap<String,String> map = (HashMap<String, String>) productService.autoUpdateBaseindex(prodCode);
        if(map.getOrDefault("o_errcode","9999").toString().equals("0001")) {
            LogbackUtils.info(String.format("%s autoUpdateBaseindex successfully, raise baseindex", prodCode));
        }
        else if (map.getOrDefault("o_errcode","9999").toString().equals("0002")) {
            LogbackUtils.info(String.format("%s autoUpdateBaseindex successfully, drop baseindex", prodCode));
        }
        else if (map.getOrDefault("o_errcode","9999").toString().equals("0000")) {
            LogbackUtils.info(String.format("%s autoUpdateBaseindex successfully, nothing changed", prodCode));
        }
        else{
            LogbackUtils.error(String.format("%s autoUpdateBaseindex failed", prodCode));
        }

    }

    private void addTradeNewRecord(String prodCode) throws Exception{
        try {
            productService.addTradeRecord(prodCode);
            LogbackUtils.info(String.format(" %s addTradeRecord successfully",prodCode));
        }
        catch (Exception ex){
            LogbackUtils.info(String.format(" %s addTradeRecord failed,"+ex.getMessage(),prodCode));
        }
    }
}
