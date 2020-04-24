package com.zdy.aipc.dao;

import com.zdy.aipc.domain.DLTTermDetail;
import com.zdy.aipc.domain.DLTTermRecord;

import java.util.List;

/**
 * @ClassName : DLTTermInfo
 * @Description :
 * @Author : dy
 * @Date: 2020-04-24 09:17
 */
public class DLTTermInfo extends DLTTermRecord {
    List<DLTTermDetail> dltTermDetailList;

    public List<DLTTermDetail> getDltTermDetailList() {
        return dltTermDetailList;
    }

    public void setDltTermDetailList(List<DLTTermDetail> dltTermDetailList) {
        this.dltTermDetailList = dltTermDetailList;
    }
}
