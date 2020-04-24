package com.zdy.aipc.dao;

import com.zdy.aipc.domain.DLTTermDetail;
import com.zdy.aipc.domain.DLTTermRecord;
import com.zdy.aipc.mapper.IDLTTermDetailMapper;
import com.zdy.aipc.mapper.IDLTTermRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : DLTTermRecordDao
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 10:45
 */
@Component
public class DLTTermDetailDao {

    @Autowired
    private IDLTTermDetailMapper idltTermDetailMapper;

    public void addTermDetail(DLTTermDetail dltTermDetail){
        idltTermDetailMapper.addTermDetail(dltTermDetail);
    }

    public List<DLTTermDetail> queryTermDetail(String termCode){
        return idltTermDetailMapper.queryTermDetail(termCode);
    }
}
