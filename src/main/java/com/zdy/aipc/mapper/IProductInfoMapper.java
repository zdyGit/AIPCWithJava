package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IProductInfoMapper {
    //获取产品列表
    List<ProductInfo> getProductInfoList();

    //获取单只产品详情
    ProductInfo getProductInfoByRID(int rid);

    ProductInfo getProductInfoByProdCode(String prodCode);

    /**
     * 自动更新基准指数
     * @param param
     */
    void autoUpdateBaseindex(Map<String,String> param);
}
