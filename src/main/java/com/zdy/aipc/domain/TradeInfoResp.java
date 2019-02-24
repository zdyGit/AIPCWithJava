package com.zdy.aipc.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zdy.aipc.utils.jsonutils.JsonConfigReader;
import com.zdy.aipc.utils.jsonutils.JsonConfigWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeInfoResp {
    private static String dafaultDataFilePath = "pbif.json";
    private static List<TradeInfo> prods = new ArrayList<TradeInfo>();

    static {
        loadTradeData();
    }

    public static void addTradeInfo(TradeInfo tradeInfo){
        for(TradeInfo tInfo:prods){
            if (tInfo.getProdCode() == tradeInfo.getProdCode()){
                prods.remove(tInfo);
                break;
            }
        }
        prods.add(tradeInfo);

        saveTradeData();
    }
    public static TradeInfo getTradeInfo(String prodCode){
        for(TradeInfo tInfo:prods){
            if (tInfo.getProdCode().equals(prodCode)){
                return tInfo;
            }
        }
        return null;
    }
    public static void loadTradeData(){
        JSONObject jData = null;
        prods.clear();
        try {
            jData = JsonConfigReader.getJsonObject(dafaultDataFilePath);
        }catch (IOException ex){
            ex.printStackTrace();
            return;
        }
        try {
            JSONArray jprodList = jData.getJSONArray("prods");
            JSONObject jprod = new JSONObject();
            TradeInfo tInfo = null;
            for (Object jd : jprodList) {
                jprod = (JSONObject) jd;
                tInfo = new TradeInfo(jprod.getString("prodCode").trim().toLowerCase());
                tInfo.setProdName(jprod.getString("prodName"));
                tInfo.setInfoUrl(jprod.getString("infoUrl"));
                tInfo.setTradeParameter(jprod.getJSONObject("tradeParameter").toJavaObject(TradeParameter.class));
                tInfo.setTradeRecord(jprod.getJSONObject("tradeRecord").toJavaObject(TradeRecord.class));

                prods.add(tInfo);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return;
        }
    }
    private static void saveTradeData(){
        String text = getJSONStr();
        if(text.trim().isEmpty()){
            return ;
        }
        JsonConfigWriter.writeFile(dafaultDataFilePath,text);
    }
    private static String getJSONStr(){
        try {
            String prodsText = JSONObject.toJSONString(prods, SerializerFeature.WriteNullStringAsEmpty);
            return String.format("{ \"prods\": %s}",prodsText);
        }catch (Exception ex){
            return "";
        }
    }
}
