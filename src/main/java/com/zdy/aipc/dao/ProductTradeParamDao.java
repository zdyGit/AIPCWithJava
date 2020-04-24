package com.zdy.aipc.dao;

import com.zdy.aipc.domain.ProductTradeParam;
import com.zdy.aipc.mapper.IProductTradeParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTradeParamDao {

    @Autowired
    private IProductTradeParamMapper iProductTradeParam;

    public ProductTradeParam getProductTradeParamByProdCode(String prodCode){
        return iProductTradeParam.getProductTradeParamByProdCode(prodCode);
    }
}
