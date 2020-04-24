package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.ProductTradeParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IProductTradeParamMapper {
    ProductTradeParam getProductTradeParamByProdCode(String prodCode);
}
