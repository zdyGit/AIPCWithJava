package com.zdy.aipc.scheduler;

import com.zdy.aipc.service.ProductRankInfoService;
import com.zdy.aipc.utils.DateUtils;
import com.zdy.aipc.utils.LogbackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductRankInfoScheduler {

    @Autowired
    private ProductRankInfoService productRankInfoService;

    private static final String cronStr = "0 0 16 * * ?";
    //private static final String cronStr = "0 9 9 * * ?";

    private static final int rowNum = 200;

    @Scheduled(cron=cronStr)
    public void downLoadTTData1() throws Exception{
        downLoadData(1,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData2() throws Exception{
        downLoadData(2,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData3() throws Exception{
        downLoadData(3,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData4() throws Exception{
        downLoadData(4,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData5() throws Exception{
        downLoadData(5,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData6() throws Exception{
        downLoadData(6,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData7() throws Exception{
        downLoadData(7,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData8() throws Exception{
        downLoadData(8,rowNum);
    }
    @Scheduled(cron=cronStr)
    public void downLoadTTData9() throws Exception{
        downLoadData(9,rowNum);
    }

    @Scheduled(cron=cronStr)
    public void downLoadTTData10() throws Exception{
        downLoadData(10,rowNum);
    }

    private void downLoadData(int rankType,int rowNum) {
        try {
            productRankInfoService.downLoadTTData(rankType, rowNum);
            LogbackUtils.info(String.format("%s 日,rankType %d 的榜单数据下载完成,共 %d 条记录", DateUtils.getDate(),rankType,rowNum));
        }
        catch (Exception ex) {
            LogbackUtils.error(String.format("%s 日,rankType %d 的榜单数据下载失败,%s", DateUtils.getDate(), rankType, ex.getMessage()));
        }
    }

}
