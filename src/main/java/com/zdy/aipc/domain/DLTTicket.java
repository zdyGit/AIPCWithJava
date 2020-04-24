package com.zdy.aipc.domain;

/**
 * @ClassName : DLTTicket
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 18:18
 */
public class DLTTicket {
    private String ticketno;
    private String term;
    private int timer;
    private String ticketCode;
    private String createDate;
    private int isvalid;

    public int getIsValid() {
        return isvalid;
    }

    public void setIsValid(int isValid) {
        this.isvalid = isValid;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String tickerCode) {
        this.ticketCode = tickerCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
