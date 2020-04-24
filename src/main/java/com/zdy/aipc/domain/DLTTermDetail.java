package com.zdy.aipc.domain;

/**
 * @ClassName : DLTTermDetail
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 10:52
 */
public class DLTTermDetail {
    private String term;
    private String num;
    private String jlevel;
    private String piece;
    private String money;

    public String getJlevel() {
        return jlevel;
    }

    public void setJlevel(String jlevel) {
        this.jlevel = jlevel;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
