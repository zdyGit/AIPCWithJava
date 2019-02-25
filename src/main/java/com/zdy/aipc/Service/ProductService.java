package com.zdy.aipc.Service;

import com.zdy.aipc.domain.Product.TradeableProduct;
import com.zdy.aipc.domain.TradeRecord;
import com.zdy.aipc.domain.productconfig.ProductConfigFactory;
import com.zdy.aipc.domain.productmarket.CYBProductMarket;
import com.zdy.aipc.domain.tradestragegy.DefaultTradeStrategy;

public class ProductService {
    public static double getLatestTradeAmount(String prodCode) throws Exception{
        TradeableProduct tradeableProduct = new TradeableProduct(prodCode);
        tradeableProduct.setProductConfig(ProductConfigFactory.getProductConfig(prodCode));
        return tradeableProduct.getLatestTradeAmount();
    }

    public static TradeRecord getLastTradeRecord(String prodCode) throws Exception{
        TradeableProduct tradeableProduct = new TradeableProduct(prodCode);
        tradeableProduct.setProductConfig(ProductConfigFactory.getProductConfig(prodCode));
        return tradeableProduct.getTradeInfo().getTradeRecord();

    }

    public static double getLatestChangeRate(String prodCode) throws Exception{
        TradeableProduct tradeableProduct = new TradeableProduct(prodCode);
        tradeableProduct.setProductConfig(ProductConfigFactory.getProductConfig(prodCode));
        return tradeableProduct.getLatestChangeRate();
    }

    public static double getLatestDaysDiff(String prodCode) throws Exception{
        TradeableProduct tradeableProduct = new TradeableProduct(prodCode);
        tradeableProduct.setProductConfig(ProductConfigFactory.getProductConfig(prodCode));
        return tradeableProduct.getLatestDaysDiff();
    }

    public static void saveTradeRecord(String prodCode) throws Exception{
        TradeableProduct tradeableProduct = new TradeableProduct(prodCode);
        tradeableProduct.setProductConfig(ProductConfigFactory.getProductConfig(prodCode));
        tradeableProduct.setTradeInfo();
    }
}
