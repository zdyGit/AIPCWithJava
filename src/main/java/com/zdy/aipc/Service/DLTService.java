package com.zdy.aipc.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdy.aipc.dao.DLTTermDetailDao;
import com.zdy.aipc.dao.DLTTermInfo;
import com.zdy.aipc.dao.DLTTermRecordDao;
import com.zdy.aipc.domain.*;
import com.zdy.aipc.mapper.IDLTChkresMapper;
import com.zdy.aipc.mapper.IDLTTermRecordMapper;
import com.zdy.aipc.mapper.IDLTTicketMapper;
import com.zdy.aipc.utils.DateUtils;
import com.zdy.aipc.utils.LogbackUtils;
import com.zdy.aipc.utils.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.LoggingMXBean;

/**
 * @ClassName : DLTService
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 09:17
 */
@Service
public class DLTService {

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private DLTTermRecordDao dltTermRecordDao;

    @Autowired
    private DLTTermDetailDao dltTermDetailDao;

    @Autowired
    private IDLTTicketMapper idltTicketMapper;

    @Autowired
    private IDLTTermRecordMapper idltTermRecordMapper;

    @Autowired
    private IDLTChkresMapper  idltChkresMapper;

    private  JSONObject getDLTTermInfo(String term) {
        if (term == null || term.trim().length() == 0) {
            return null;
        }
        JSONArray jres = new JSONArray();
        String url = String.format(DLTUrl.dltUrl + "?_ltype=4&_term=%s", term);
        LogbackUtils.info("查询DLT-" + term + "期信息，" + url);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            jres = JSONArray.parseArray(responseEntity.getBody());
        } catch (Exception ex) {
            LogbackUtils.error("查询失败：" + ex.getMessage());
            return null;
        }
        return jres.getJSONObject(0);
    }

    public boolean addDLTRecord(String term){

        LogbackUtils.info(String.format("查询%s期信息",term));

        DLTTermRecord dltTermRecordTemp = dltTermRecordDao.queryTermRecord(term);
        if(dltTermRecordTemp != null){
            LogbackUtils.info(String.format("%s期信息已存在",term));
            return true;
        }

        JSONObject jTermInfo = getDLTTermInfo(term);
        if(jTermInfo == null){
            LogbackUtils.info(String.format("%s期信息获取失败",term));
            return false;
        }
        if(!jTermInfo.containsKey("lottery")){
            LogbackUtils.info(String.format("%s期信息获取失败",term));
            return false;
        }
        JSONObject jlottery = jTermInfo.getJSONObject("lottery");
        try {
            String curdays = DateUtils.getDate("yyyyMMdd");
            if(Integer.parseInt(jlottery.getString("openTime_fmt1"))>Integer.parseInt(curdays)){
                LogbackUtils.info("该期尚未到来");
                return false;
            }
        }catch (Exception ex){
            LogbackUtils.error(ex.getMessage());
            return false;
        }

        DLTTermRecord dltTermRecord = new DLTTermRecord();
        dltTermRecord.setTerm(jlottery.getString("term"));
        dltTermRecord.setCodeNumber5(jlottery.getString("number").split("-")[0]);
        dltTermRecord.setCodeNumber2(jlottery.getString("number").split("-")[1]);
        dltTermRecord.setsTime(jlottery.getString("fTime"));
        try {
            dltTermRecord.setUpdateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        }catch (Exception ex) {
            LogbackUtils.error(ex.getMessage());
            return false;
        }

        JSONArray jdetails = jTermInfo.getJSONArray("details");
        for(int i=0;i<jdetails.size();i++){
            DLTTermDetail dltTermDetail = new DLTTermDetail();
            dltTermDetail.setTerm(dltTermRecord.getTerm());
            dltTermDetail.setNum(jdetails.getJSONObject(i).getString("num"));
            dltTermDetail.setJlevel(jdetails.getJSONObject(i).getString("level"));
            dltTermDetail.setPiece(jdetails.getJSONObject(i).getString("piece"));
            dltTermDetail.setMoney(jdetails.getJSONObject(i).getString("money"));
            dltTermDetailDao.addTermDetail(dltTermDetail);
        }

        dltTermRecordDao.addTermRecord(dltTermRecord);
        return true;
    }

    public DLTTermRecord queryLatestTermRecord(){
        return dltTermRecordDao.queryLatestTermRecord();
    }

    public DLTTermInfo queryDLTTermInfo(String termCode){
        DLTTermInfo dltTermInfo = new DLTTermInfo();
        DLTTermRecord dltTermRecord = idltTermRecordMapper.queryTermRecord(termCode);
        BeanUtils.copyProperties(dltTermRecord,dltTermInfo);
        List<DLTTermDetail> dltTermDetails = dltTermDetailDao.queryTermDetail(dltTermRecord.getTerm());
        dltTermInfo.setDltTermDetailList(dltTermDetails);
        return dltTermInfo;
    }


    public void checkTicket(){
        LogbackUtils.info(String.format("开始验奖"));
        List<DLTTicket> ticketList = idltTicketMapper.queryValidTicket();
        for(DLTTicket ticket :ticketList){
            int checkCount = 0;
            List<DLTTermRecord> termRecords = idltTermRecordMapper.queryLimitTermRecord(ticket.getTerm(),ticket.getTimer());
            for(DLTTermRecord dltTermRecord:termRecords){
                LogbackUtils.info(String.format("开始验奖：%s, 第%s 期",ticket.getTicketno(),dltTermRecord.getTerm()));
                DLTChkres dltChkres = idltChkresMapper.queryChkres(ticket.getTicketno(),dltTermRecord.getTerm());
                if(dltChkres != null ){
                    LogbackUtils.info(String.format("%s, 第%s 期已验过，本次跳过",ticket.getTicketno(),dltTermRecord.getTerm()));
                    checkCount = checkCount + 1;
                    continue;
                }
                String tempNumber = dltTermRecord.getCodeNumber5()+" "+dltTermRecord.getCodeNumber2();
                int [] sourceCode = new int[7];
                int [] tarCode = new int[7];

                for(int i = 0 ;i<7;i++){
                    sourceCode[i] = Integer.parseInt(ticket.getTicketCode().split(",")[i]);
                    tarCode[i] = Integer.parseInt(tempNumber.split(" ")[i]);
                }
                HashMap<String,Integer> chkRes = codeCheck(sourceCode,tarCode);

                dltChkres = new DLTChkres();
                dltChkres.setTicketno(ticket.getTicketno());
                dltChkres.setTerm(dltTermRecord.getTerm());
                dltChkres.setChkres(chkRes.get("chkRes"));
                dltChkres.setMatch5(chkRes.get("match5"));
                dltChkres.setMatch2(chkRes.get("match2"));
                try {
                    dltChkres.setChktime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                }catch (Exception ex)
                {

                }
                idltChkresMapper.addChkres(dltChkres);
                checkCount = checkCount + 1;
            }

            if(checkCount == ticket.getTimer() ){
                idltTicketMapper.updateTicketValid(ticket.getTicketno());
            }
        }
        LogbackUtils.info(String.format("验奖结束"));
    }

    private HashMap codeCheck(int[] soureCode, int[] targeCode){
        HashMap<String,Integer> chk = new HashMap<String, Integer>();
        LogbackUtils.info(String.format("ticket:%s", Arrays.toString(soureCode)));
        LogbackUtils.info(String.format("code:%s", Arrays.toString(targeCode)));
        int match5 = 0 ;
        int match2 = 0;
        int i=0,j=0;
        while(i<5 && j<5){
            if(soureCode[i] == targeCode[j]){
                match5 = match5 + 1;
                i = i+1;
                j = j +1;
            }else if(soureCode[i] < targeCode[j]){
                i = i+1;
            }
            else {
                j = j +1;
            }
        }
        i = 5;
        j = 5;
        while(i<7&&j<7){
            if(soureCode[i] == targeCode[j]){
                match2 = match2 + 1;
                i = i+1;
                j = j +1;
            }else if(soureCode[i] < targeCode[j]){
                i = i+1;
            }
            else {
                j = j +1;
            }
        }
        int chkRes = 0;
        if(match5 == 5 && match2 == 2)
            chkRes = 1;
        else if(match5 == 5 && match2 == 1)
            chkRes = 2;
        else if(match5 == 5)
            chkRes = 3;
        else if(match5 == 4 && match2 == 2)
            chkRes = 4;
        else if(match5 == 4 && match2 == 1)
            chkRes = 5;
        else if(match5 == 3 && match2 == 2)
            chkRes = 6;
        else if(match5 == 4)
            chkRes = 7;
        else if((match5 == 3 && match2 == 1) || (match5 ==2 && match2 == 2))
            chkRes = 8;
        else if((match5 == 3)||(match5 == 1 && match2 == 2)||(match5 == 2 && match2 == 1)||(match2 == 2))
            chkRes = 9;
        else
            chkRes = 0;

        LogbackUtils.info(String.format("check Result:match5 %d,match2 %d, finally %d",match5,match2,chkRes));
        chk.put("chkRes",chkRes);
        chk.put("match5",match5);
        chk.put("match2",match2);
        return chk;
    }
}
