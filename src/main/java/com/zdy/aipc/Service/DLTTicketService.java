package com.zdy.aipc.service;

import com.zdy.aipc.domain.DLTChkres;
import com.zdy.aipc.domain.DLTTicket;
import com.zdy.aipc.domain.DLTTicketChkRes;
import com.zdy.aipc.mapper.IDLTChkresMapper;
import com.zdy.aipc.mapper.IDLTTicketMapper;
import com.zdy.aipc.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : DLTTicketService
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 19:29
 */
@Service
public class DLTTicketService {


    @Autowired
    private IDLTTicketMapper idltTicketMapper;

    @Autowired
    private IDLTChkresMapper idltChkresMapper;

    public void addTicket(DLTTicket dltTicket){
        try {
            dltTicket.setTicketno("TICKET_" + dltTicket.getTicketno());
        }catch (Exception ex){

        }
        dltTicket.setIsValid(1);
        try {
            dltTicket.setCreateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        }catch (Exception ex){

        }
        idltTicketMapper.addTicket(dltTicket);
    }

    public DLTTicketChkRes getTicketinfo(String ticketno){
        DLTTicket ticket = idltTicketMapper.querySingleTicket(ticketno);
        if(ticket == null){
            return null;
        }

        List<DLTChkres> chkresList = idltChkresMapper.queryChkresByticketno(ticket.getTicketno());
        DLTTicketChkRes dltTicketChkRes = new DLTTicketChkRes();
        BeanUtils.copyProperties(ticket,dltTicketChkRes);
        dltTicketChkRes.setChkresList(chkresList);
        return dltTicketChkRes;
    }
}
