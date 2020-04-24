package com.zdy.aipc.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.zdy.aipc.dao.DLTTermInfo;
import com.zdy.aipc.domain.DLTTicket;
import com.zdy.aipc.domain.DLTTicketChkRes;
import com.zdy.aipc.service.DLTService;
import com.zdy.aipc.service.DLTTicketService;
import com.zdy.aipc.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : DLTController
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 09:33
 */
@RestController
@RequestMapping(value = "/v1/dlt")
public class DLTController {

    @Autowired
    private DLTService dltService;

    @Autowired
    private DLTTicketService dltTicketService;

    @GetMapping(value = "/addTerm")
    public String addTermInfo(@RequestParam(required = true) String termCode){
        dltService.addDLTRecord(termCode);
        return "success";
    }

    @GetMapping(value = "/termInfo")
    public DLTTermInfo getTermInfo(@RequestParam(required = true) String termCode){
        DLTTermInfo dltTermInfo = dltService.queryDLTTermInfo(termCode);
        return dltTermInfo;
    }

    @PostMapping(value = "/addTicket")
    public String addTicket(@RequestBody DLTTicket dltTicket){
        if(StringUtils.isNullOrEmpty(dltTicket.getTicketno())){
            return "failed,ticketno is empty";
        }

        if(StringUtils.isNullOrEmpty(dltTicket.getTerm())){
            return "failed,term is empty";
        }
        if(StringUtils.isNullOrEmpty(dltTicket.getTicketCode())){
            return "failed,ticketCode not empty";
        }
        if(dltTicket.getTicketCode().split(",").length != 7){
            return "failed,ticketCode num is error , 7 recommend";
        }
        if(dltTicket.getTimer()<1){
            return "timer > 0";
        }
        dltTicketService.addTicket(dltTicket);
        return "success";
    }

    @GetMapping(value = "/check")
    public void checkTicket(){
        dltService.checkTicket();
    }

    @GetMapping(value="/checkInfo")
    public DLTTicketChkRes getTicketinfo(@RequestParam(required = true) String ticketno){
        return dltTicketService.getTicketinfo(ticketno);
    }
}
