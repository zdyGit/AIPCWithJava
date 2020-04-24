package com.zdy.aipc.domain;

import java.util.List;

/**
 * @ClassName : DLTTicketChkRes
 * @Description :
 * @Author : dy
 * @Date: 2020-04-24 08:56
 */
public class DLTTicketChkRes extends DLTTicket {

    private List<DLTChkres> chkresList;

    public List<DLTChkres> getChkresList() {
        return chkresList;
    }

    public void setChkresList(List<DLTChkres> chkresList) {
        this.chkresList = chkresList;
    }
}
