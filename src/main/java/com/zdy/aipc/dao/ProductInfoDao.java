package com.zdy.aipc.dao;

import com.zdy.aipc.domain.ProductInfo;
import com.zdy.aipc.mapper.IProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductInfoDao {
    @Autowired
    IProductInfoMapper iProductInfoMapper;

    public List<ProductInfo> getProductInfoList(){
        return iProductInfoMapper.getProductInfoList();
    }

    public ProductInfo getProductInfoByRID(int rid){
        return iProductInfoMapper.getProductInfoByRID(rid);
    }

    public ProductInfo getProductInfoByProdCode(String prodCode){
        return iProductInfoMapper.getProductInfoByProdCode(prodCode);
    }

    public  void autoUpdateBaseindex(Map<String,String> param){
        iProductInfoMapper.autoUpdateBaseindex(param);
    }

}
