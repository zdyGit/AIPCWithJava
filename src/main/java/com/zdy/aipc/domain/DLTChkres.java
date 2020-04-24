package com.zdy.aipc.domain;

/**
 * @ClassName : DLTChkres
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 21:10
 */
public class DLTChkres {

    private String ticketno;
    private String term;
    private int chkres;
    private String chktime;
    private int match5;
    private int match2;

    public int getMatch5() {
        return match5;
    }

    public void setMatch5(int match5) {
        this.match5 = match5;
    }

    public int getMatch2() {
        return match2;
    }

    public void setMatch2(int match2) {
        this.match2 = match2;
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

    public int getChkres() {
        return chkres;
    }

    public void setChkres(int chkres) {
        this.chkres = chkres;
    }

    public String getChktime() {
        return chktime;
    }

    public void setChktime(String chktime) {
        this.chktime = chktime;
    }
}
