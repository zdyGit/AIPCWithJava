package com.zdy.aipc.domain;

import com.zdy.aipc.utils.LogbackUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class TTJJUrl {
    private static HashMap<String,String> rankUrlKeyWorld = new HashMap<>();
    private static final String ttBaseUrl = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&qdii=&tabSubtype=,,,,,&pi=1&dx=1";
    private static HashMap<String,String > sortTypeMap = new HashMap<>();
    static {
        rankUrlKeyWorld.put("1","rzdf");
        rankUrlKeyWorld.put("2","zzf");
        rankUrlKeyWorld.put("3","1yzf");
        rankUrlKeyWorld.put("4","3yzf");
        rankUrlKeyWorld.put("5","6yzf");
        rankUrlKeyWorld.put("6","1nzf");
        rankUrlKeyWorld.put("7","2nzf");
        rankUrlKeyWorld.put("8","3nzf");
        rankUrlKeyWorld.put("9","jnzf");
        rankUrlKeyWorld.put("10","lnzf");

        sortTypeMap.put("1","asc");
        sortTypeMap.put("2","desc");
    }

    // &v=0.42152252525&sc=rzdf&st=desc&sd=2018-08-20&ed=2019-08-20&pn=50
    public static String getUrl(int rankType,String startDate,String endDate,int sortType,int rownum){
        String rankKeyWord = rankUrlKeyWorld.getOrDefault(Integer.toString(rankType),"rzdf");
        String soryTypeWord = sortTypeMap.getOrDefault(Integer.toString(sortType),"asc");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(rownum <=0){
            rownum = 50;
        }
        try{
            simpleDateFormat.parse(startDate);
        }
        catch (Exception ex){
            startDate = simpleDateFormat.format(new Date()).toString();
        }

        try{
            simpleDateFormat.parse(endDate);
        }
        catch (Exception ex){
            endDate = simpleDateFormat.format(new Date()).toString();
        }
        double v = new Random().nextDouble();
        String url = String.format("%s&sc=%s&st=%s&sd=%s&ed=%s&pn=%d&v=%f",ttBaseUrl,rankKeyWord,soryTypeWord,startDate,endDate,rownum,v);
        LogbackUtils.info(url);
        return url;
    }
}
