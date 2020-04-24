package com.zdy.aipc.service;


import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.dao.ProductRankInfoDao;
import com.zdy.aipc.domain.ProductRankInfo;
import com.zdy.aipc.domain.TTJJUrl;
import com.zdy.aipc.utils.DateUtils;
import com.zdy.aipc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductRankInfoService {
    @Autowired
    private ProductRankInfoDao productRankInfoDao;

    @Autowired
    private RestTemplate restTemplate ;

    public JSONObject getTTData(int rankType,int sortType,int rowNum) throws Exception{
        String startDate = DateUtils.getDate();
        String endDate = startDate;
        String url = TTJJUrl.getUrl(rankType,startDate,endDate,sortType,rowNum);
        ResponseEntity<String> responseEntity =  restTemplate.getForEntity(url,String.class);

        String responseStr = responseEntity.getBody().split("=").length<2?"":responseEntity.getBody().split("=")[1];
        Pattern pattern1 = Pattern.compile(".*?\\[(.*?)\\].*?");
        Matcher matcher1 = pattern1.matcher(responseStr);
        String matchRes = "";
        if(matcher1.matches()) {
            matchRes = matcher1.group(1);
        }
        matchRes = StringUtils.trimFirstAndLastChar(matchRes.trim(),'\"');
        String[] itemList = matchRes.split("\",\"");
        ArrayList<String> itemarr = new ArrayList<>();
        for(String item:itemList) {
            itemarr.add(item);
        }

        JSONObject jdata = new JSONObject();
        jdata.put("rankType",rankType);
        jdata.put("startDate",startDate);
        jdata.put("endDate",endDate);
        jdata.put("sortType",sortType);
        jdata.put("data",itemarr);
        return jdata;
    }

    public String downLoadTTData(int rankType,int rowNum) throws Exception{
        JSONObject jdata = getTTData(rankType,2,rowNum);
        saveTTDate(jdata);
        return "SUCCESSFULLY";
    }

    private void saveTTDate(JSONObject jdata) throws Exception{
        if(null == jdata){
            return ;
        }

        List<Integer> postList = new ArrayList<Integer>();
        postList.add(0);
        postList.add(6);
        postList.add(7);
        postList.add(8);
        postList.add(9);
        postList.add(10);
        postList.add(11);
        postList.add(12);
        postList.add(13);
        postList.add(14);
        postList.add(15);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");

        ArrayList<String> arrayList =(ArrayList<String>)jdata.get("data");
        List<ProductRankInfo> productRankInfoList = new ArrayList<ProductRankInfo>();

        for(int index = 0 ;index < arrayList.size();index ++){
            String [] dataList = arrayList.get(index).split(",");
            ProductRankInfo productRankInfo = new ProductRankInfo();
            productRankInfo.setRn(index+1);
            productRankInfo.setProdName(dataList[1]);
            productRankInfo.setProdCode(dataList[0]);

            String sysdt = dateFormat2.format(dateFormat.parse(dataList[3]));
            productRankInfo.setSysdt(sysdt);
            productRankInfo.setRankType(jdata.getInteger("rankType"));
            try {
                productRankInfo.setRate(Double.parseDouble(dataList[(int) postList.get(productRankInfo.getRankType())]));
            }catch (Exception ex){
                productRankInfo.setRate(0.00);
            }
            try {
                productRankInfo.setNav(Double.parseDouble(dataList[4]));
            }
            catch (Exception ex){
                productRankInfo.setNav(0.00);
            }
            productRankInfo.setCreatedate(DateUtils.getFullDate());

            productRankInfoList.add(productRankInfo);
        }

        if(productRankInfoList.size()>0){
            productRankInfoDao.delProductRankInfoBySysdt(DateUtils.getDate(),jdata.getInteger("rankType"));
            productRankInfoDao.addBatchProductRankInfo(productRankInfoList);
        }
    }


}
