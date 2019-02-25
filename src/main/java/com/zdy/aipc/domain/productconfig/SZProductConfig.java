package com.zdy.aipc.domain.productconfig;

import com.zdy.aipc.domain.Product.TradeableProduct;
import com.zdy.aipc.domain.productmarket.CYBProductMarket;
import com.zdy.aipc.domain.productmarket.ProductMarket;
import com.zdy.aipc.domain.productmarket.SZProductMarket;
import com.zdy.aipc.domain.tradestragegy.DefaultTradeStrategy;
import com.zdy.aipc.domain.tradestragegy.ITradeStrategy;

public class SZProductConfig implements IProductConfig {

    @Override
    public void setTradeStragegy(TradeableProduct p) {
        ITradeStrategy tradeStragegy = new DefaultTradeStrategy(p);
        p.setTradeStrategy(tradeStragegy);
    }

    @Override
    public void setProductMarkey(TradeableProduct p) {
        ProductMarket productMarkey = new SZProductMarket();
        p.setProductMarket(productMarkey);
    }
}
