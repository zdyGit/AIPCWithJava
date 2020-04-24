package com.zdy.aipc.domain;

/**
 * @ClassName : DLTTermRecord
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 10:36
 */
public class DLTTermRecord {
    private String term;
    private String codeNumber5;
    private String codeNumber2;
    private String sTime;
    private String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCodeNumber5() {
        return codeNumber5;
    }

    public void setCodeNumber5(String codeNumber5) {
        this.codeNumber5 = codeNumber5;
    }

    public String getCodeNumber2() {
        return codeNumber2;
    }

    public void setCodeNumber2(String codeNumber2) {
        this.codeNumber2 = codeNumber2;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }
}
