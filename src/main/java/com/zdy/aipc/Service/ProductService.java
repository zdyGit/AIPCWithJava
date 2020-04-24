package com.zdy.aipc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.dao.ProductInfoDao;
import com.zdy.aipc.dao.ProductTradeParamDao;
import com.zdy.aipc.dao.ProductTradeRecordDao;
import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.domain.ProductTradeParam;
import com.zdy.aipc.domain.ProductTradeRecord;
import com.zdy.aipc.service.productmarket.CYBProductMarket;
import com.zdy.aipc.service.productmarket.SZProductMarket;
import com.zdy.aipc.service.tradestragegy.DefaultTradeStrategy;
import com.zdy.aipc.service.tradestragegy.ITradeStrategy;
import com.zdy.aipc.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductTradeRecordDao productTradeRecordDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductTradeParamDao productTradeParamDao;

    @Autowired
    private DefaultTradeStrategy iTradeStrategy;

    /***
     * 获取产品最新交易金额
     */
    public JSONObject getLatestTradeAmount(String prodCode) throws Exception{
        ProductInfo productInfo = getProductInfo(prodCode);
        return productInfo.getLatestTradeAmount();
    }

    /***
     * 获取所有产品最新交易金额
     */
    public JSONArray getLatestAllTradeAmount() throws Exception{
        List<ProductInfo> productInfoList = getProductList();
        JSONArray jres = new JSONArray();
        for(int i = 0;i< productInfoList.size();i++){
            ProductInfo productInfo = productInfoList.get(i);
            jres.add(getLatestTradeAmount(productInfo.getProdCode()));
        }
        return jres;
    }

    public ProductInfo getProductInfo(String  prodCode){
        ProductInfo productInfo = productInfoDao.getProductInfoByProdCode(prodCode);
        if(prodCode.equals("a1")) {
            productInfo.setProductMarket(new CYBProductMarket());
        }
        else{
            productInfo.setProductMarket(new SZProductMarket());
        }
        productInfo.setiTradeStrategy(iTradeStrategy);
        return productInfo;
    }

    public List<ProductInfo> getProductList(){
        return productInfoDao.getProductInfoList();
    }

    /**
     * 获取最新产品信息
     * @param prodCode
     * @return
     * @throws Exception
     */
    public JSONObject getLatestProductInfo(String prodCode)throws Exception{

        ProductInfo productInfo = getProductInfo(prodCode);
        JSONObject jres = new JSONObject();
        jres.put("prodCode",productInfo.getProdCode());
        jres.put("prodName",productInfo.getProdName());
        jres.put("latestPrice",productInfo.getLatestPrice());
        jres.put("latestDropRate",String.format("%.2f%%",productInfo.getLatestDropRate()*100.0));
        return jres;
    }

    /**
     * 获取最新所有产品信息
     * @return
     * @throws Exception
     */
    public JSONArray getLatestAllProductInfo() throws Exception{
        List<ProductInfo> productInfoList = getProductList();
        JSONArray jres = new JSONArray();
        for(int i = 0;i< productInfoList.size();i++){
            ProductInfo productInfo = productInfoList.get(i);
            jres.add(getLatestProductInfo(productInfo.getProdCode()));
        }
        return jres ;
    }
    //获取产品上次交易记录
    public ProductTradeRecord getLastTradeRecord(String prodCode) throws Exception{
        ProductTradeRecord productTradeRecord = productTradeRecordDao.getLatestProductTradeRecord(prodCode);
        return productTradeRecord;
    }
    //获取产品交易参数
    public ProductTradeParam getProductTradeParam(String prodCode) throws Exception{
        ProductTradeParam productTradeParam = productTradeParamDao.getProductTradeParamByProdCode(prodCode);
        return productTradeParam;
    }

    //获取最新涨跌幅
    public double getLatestDropRate(String prodCode) throws Exception{
        ProductInfo productInfo = productInfoDao.getProductInfoByProdCode(prodCode);
        CYBProductMarket cybProductMarket = new CYBProductMarket();
        HashMap latestTradeInfo = cybProductMarket.getLatestTradeInfo(productInfo);
        return (double)latestTradeInfo.getOrDefault("latestDropRate",0);
    }
    //获取最新净值
    public double getLatestPrice(String prodCode) throws Exception{
        ProductInfo productInfo = productInfoDao.getProductInfoByProdCode(prodCode);
        CYBProductMarket cybProductMarket = new CYBProductMarket();
        HashMap latestTradeInfo = cybProductMarket.getLatestTradeInfo(productInfo);
        return (double)latestTradeInfo.getOrDefault("latestPrice",0);
    }

    public void addTradeRecord(String prodCode) throws Exception{
        ProductTradeRecord productTradeRecord = new ProductTradeRecord();
        ProductInfo productInfo = getProductInfo(prodCode);
        JSONObject tradeInfo = productInfo.getLatestTradeAmount();
        String timeNow = DateUtils.getDate("HHmmss");
        if(Integer.parseInt(timeNow)< Integer.parseInt("150000")){
            return ;
        }
        if(tradeInfo.getInteger("tradeType") == 0 ){
            return ;
        }

        productTradeRecord.setProdCode(productInfo.getProdCode());
        productTradeRecord.setTradeAmount(tradeInfo.getDouble("tradeAmount"));
        productTradeRecord.setTradeDate(DateUtils.getDate());
        productTradeRecord.setTradeIndex(productInfo.getLatestPrice());
        productTradeRecord.setTradeType(tradeInfo.getInteger("tradeType"));
        productTradeRecordDao.addProductTradeRecord(productTradeRecord);
    }

    public Map<String, String> autoUpdateBaseindex(String prodCode) throws Exception{
        HashMap<String,String> map = new HashMap<>();
        map.put("p_prodCode",prodCode);
        map.put("o_errcode","");
        productInfoDao.autoUpdateBaseindex(map);
        return map;
    }
}
