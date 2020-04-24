package com.zdy.aipc.scheduler;

import com.zdy.aipc.domain.DLTTermRecord;
import com.zdy.aipc.service.DLTService;
import com.zdy.aipc.utils.DateUtils;
import com.zdy.aipc.utils.LogbackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName : DLTTermScheduler
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 15:45
 */
@Component
public class DLTTermScheduler {

    @Autowired
    private DLTService dltService;

    private static final String connstr = "0 0 21 * * ?";

    @Scheduled(cron=connstr)
    public void updateTermInfo(){

        LogbackUtils.info(String.format("开始更新DLT数据，%s", DateUtils.getFullDate()));
        DLTTermRecord dltTermRecord = dltService.queryLatestTermRecord();
        String latestTermCode = "";
        try {
            latestTermCode = DateUtils.getDate("yyyyMMdd").substring(0,2)+"001";
        }catch (Exception ex){
            return ;
        }
        if(dltTermRecord != null){
            latestTermCode = dltTermRecord.getTerm();
        }
        while(true){

            boolean res = dltService.addDLTRecord(latestTermCode);
            if(!res){
                break;
            }

            Integer temp = Integer.parseInt(latestTermCode)+1;
            latestTermCode = temp.toString();
        }

        LogbackUtils.info(String.format("本次更新完成，%s", DateUtils.getFullDate()));
    }

    @Scheduled(cron="0 9 21 * * ?")
    public void check(){
        dltService.checkTicket();
    }
}
