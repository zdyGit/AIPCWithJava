package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.DLTTicket;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : IDLTTicketMapper
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 18:37
 */
@Mapper
@Component
public interface IDLTTicketMapper {
    void addTicket(DLTTicket dltTicket);

    List<DLTTicket> queryValidTicket();

    DLTTicket querySingleTicket(String ticketno);

    void updateTicketValid(String ticketno);
}
