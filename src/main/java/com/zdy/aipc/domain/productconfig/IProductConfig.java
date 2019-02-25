package com.zdy.aipc.domain.productconfig;

import com.zdy.aipc.domain.Product.TradeableProduct;
import com.zdy.aipc.domain.productmarket.ProductMarket;
import com.zdy.aipc.domain.tradestragegy.ITradeStrategy;

public interface IProductConfig {
    void setTradeStragegy(TradeableProduct p);
    void setProductMarkey(TradeableProduct p);
}
