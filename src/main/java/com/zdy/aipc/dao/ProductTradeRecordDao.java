package com.zdy.aipc.dao;

import com.zdy.aipc.domain.ProductTradeRecord;
import com.zdy.aipc.mapper.IProductTradeRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTradeRecordDao {

    @Autowired
    private IProductTradeRecordMapper iProductTradeRecord;

    /***
     * 获取最新的交易记录（按交易日期逆序）
     * @param prodCode
     * @return
     */
    public List<ProductTradeRecord> getProductTradeRecordByProdCode(String prodCode){
        return iProductTradeRecord.getProductTradeRecordByProdCode(prodCode);
    }

    public ProductTradeRecord getLatestProductTradeRecord(String prodCode){
        List<ProductTradeRecord> productTradeRecordList = getProductTradeRecordByProdCode(prodCode);
        if(productTradeRecordList.size() == 0){
            return null;
        }
        return productTradeRecordList.get(0);
    }

    public void addProductTradeRecord(ProductTradeRecord productTradeRecord){
        iProductTradeRecord.insertProductTradeRecord(productTradeRecord);
    }
}
