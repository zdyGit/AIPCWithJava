package com.zdy.aipc.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zdy.aipc.domain.AbstractProduct;
import com.zdy.aipc.domain.Product;
import com.zdy.aipc.domain.TradeRecord;
import com.zdy.aipc.domain.productmarket.CYBProductMarket;
import com.zdy.aipc.domain.tradestragegy.DefaultTradeStrategy;

public class ProductService {
    public static double getLatestTradeAmount(String prodCode) throws Exception{
        Product product = new Product(prodCode);
        product.setTradeStrategy(new DefaultTradeStrategy(product));
        product.setProductMarket(new CYBProductMarket(product.getProdCode()));
        return product.getLatestTradeAmount();
    }

    public static TradeRecord getLastTradeRecord(String prodCode) throws Exception{
        Product product = new Product(prodCode);
        product.setTradeStrategy(new DefaultTradeStrategy(product));
        product.setProductMarket(new CYBProductMarket(product.getProdCode()));
        return product.getTradeInfo().getTradeRecord();

    }

    public static double getLatestChangeRate(String prodCode) throws Exception{
        Product product = new Product(prodCode);
        product.setTradeStrategy(new DefaultTradeStrategy(product));
        product.setProductMarket(new CYBProductMarket(product.getProdCode()));
        return product.getLatestChangeRate();
    }

    public static double getLatestDaysDiff(String prodCode) throws Exception{
        Product product = new Product(prodCode);
        product.setTradeStrategy(new DefaultTradeStrategy(product));
        product.setProductMarket(new CYBProductMarket(product.getProdCode()));
        return product.getLatestDaysDiff();
    }
}
