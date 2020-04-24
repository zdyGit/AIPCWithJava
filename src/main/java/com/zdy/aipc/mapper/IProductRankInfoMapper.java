package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.ProductRankInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IProductRankInfoMapper {


    /**
     * 查询某一产品的历史排名情况
     * @param prodCode
     * @return
     */
    List<ProductRankInfo> getProductRankInfoByProdCode(String prodCode);

    /**
     * 查询某一天的各排名情况
     * @param sysdt
     * @return
     */
    List<ProductRankInfo> getProductRankInfoBySysdt(String sysdt);

    /**
     * 查询某一排名的历史产品列表
     * @param rankType
     * @return
     */
    List<ProductRankInfo> getProductRankInfoByRankType(int rankType);

    /**
     * 查询某一天，某一排序下的产品列表
     * @param sysdt
     * @param rankType
     * @return
     */
    List<ProductRankInfo> getProductRankInfoBySysdtAndRankType(String sysdt,int rankType);

    /**
     * 查询某一天，某一产品的排行榜
     * @param prodCode
     * @param sysdt
     * @return
     */
    List<ProductRankInfo> getProductRankInfoByProdCodeAndSysdt(String prodCode,String sysdt);

    /**
     * 新增排行榜记录
     * @param productRankInfo
     */
    void addProductRankInfo(ProductRankInfo productRankInfo);

    /**
     * 新增排行榜记录(批量)
     * @param productRankInfo
     */
    void addBatchProductRankInfo(List<ProductRankInfo> productRankInfo);

    /**
     * 删除某一天，某一排行榜的产品
     * @param sysdt
     * @param rankType
     */
    void delProductRankInfoBySysdtAndRankType(String sysdt,int rankType);

    /**
     * 删除某一天，某一排行榜的产品
     * @param sysdt
     */
    void delProductRankInfoBySysdt(String sysdt,int rankType);



}
