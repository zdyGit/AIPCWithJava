package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.ProductTradeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductTradeRecordMapper {
    List<ProductTradeRecord> getProductTradeRecordByProdCode(String prodCode);

    void insertProductTradeRecord(ProductTradeRecord productTradeRecord);
}
