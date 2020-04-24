package com.zdy.aipc.domain;

import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.service.productmarket.ProductMarket;
import com.zdy.aipc.service.tradestragegy.ITradeStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProductInfo {

    private int rid;
    private String prodCode;
    private String prodName;
    private String infoUrl;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }


    /**
    产品行情市场
     */
    private ProductMarket productMarket;

    /**
     * 交易策略
     */
    private ITradeStrategy iTradeStrategy;


    public void setProductMarket(ProductMarket productMarket){
        this.productMarket = productMarket;
    }

    public void setiTradeStrategy(ITradeStrategy iTradeStrategy){
        this.iTradeStrategy = iTradeStrategy;
    }
    /**
     * 获取最新产品净值
     */
    public double getLatestPrice()  throws  Exception{
        if(productMarket == null){
            throw new Exception("productMarket not set") ;
        }
        HashMap<String,Object> tradeData= productMarket.getLatestTradeInfo(this);
        return (double)tradeData.getOrDefault("latestPrice",0);
    }

    /**
     * 获取最新产品涨跌幅
     */
    public double getLatestDropRate() throws  Exception{
        if(productMarket == null){
            throw new Exception("productMarket not set") ;
        }
        HashMap<String,Object>  tradeData= productMarket.getLatestTradeInfo(this);
        return (double)tradeData.getOrDefault("latestDropRate",0);
    }

    /**
     * 获取产品当前投资金额
     * @return
     * @throws Exception
     */
    public JSONObject getLatestTradeAmount() throws Exception{
        if(this.iTradeStrategy == null){
            throw new Exception("iTradeStrategy not set") ;
        }
        return this.iTradeStrategy.getTradeInfo(this);
    }
}
