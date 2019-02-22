package com.zdy.aipc.domain;

import com.zdy.aipc.utils.ConfigReader;
import com.alibaba.fastjson.*;

public class TradeRecord {
    private String prodCode;

    public double lastTradeDate;
    public double lastTradePrice;

    public TradeRecord(String prodCode){
        this.prodCode = prodCode;
    }

    private void loadTradeVaue(){
        JSONObject jData ;

        try {
            jData = ConfigReader.configReaderToJson("");
        }catch (Exception ex){
            
        }

    }

}
