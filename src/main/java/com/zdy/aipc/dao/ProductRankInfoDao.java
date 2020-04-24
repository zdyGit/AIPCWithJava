package com.zdy.aipc.dao;

import com.zdy.aipc.domain.ProductRankInfo;
import com.zdy.aipc.mapper.IProductRankInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductRankInfoDao {
    @Autowired
    private IProductRankInfoMapper iProductRankInfoMapper;

    public void addProductRankInfo(ProductRankInfo productRankInfo){
        iProductRankInfoMapper.addProductRankInfo(productRankInfo);
    }

    public void addBatchProductRankInfo(List<ProductRankInfo> productRankInfoList){
        iProductRankInfoMapper.addBatchProductRankInfo(productRankInfoList);
    }

    public void delProductRankInfoBySysdtAndRankType(String sysdt,int rankType){
        iProductRankInfoMapper.delProductRankInfoBySysdtAndRankType(sysdt,rankType);
    }

    public void delProductRankInfoBySysdt(String sysdt,int rankType){
        iProductRankInfoMapper.delProductRankInfoBySysdt(sysdt,rankType);
    }


}
