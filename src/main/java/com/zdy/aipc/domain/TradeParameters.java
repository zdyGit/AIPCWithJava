package com.zdy.aipc.domain;

import java.util.HashMap;

public class TradeParameters {
    private static HashMap<String,TradeParameter> tradeParamters = new HashMap<String,TradeParameter>();

    public static void addTradeParameter(String key,TradeParameter tradeParameter){
        tradeParamters.put(key,tradeParameter);
    }

    public static TradeParameter getTradeParameter(String key){
        if(tradeParamters.containsKey(key)){
            return tradeParamters.get(key);
        }
        return null;
    }

}
