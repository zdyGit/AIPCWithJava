package com.zdy.aipc.dao;

import com.zdy.aipc.domain.DLTTermRecord;
import com.zdy.aipc.mapper.IDLTTermRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : DLTTermRecordDao
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 10:45
 */
@Component
public class DLTTermRecordDao {

    @Autowired
    private IDLTTermRecordMapper idltTermRecordMapper;

    public void addTermRecord(DLTTermRecord dltTermRecord){
        idltTermRecordMapper.addTermRecord(dltTermRecord);
    }

    public DLTTermRecord queryTermRecord(String termCode){
        return idltTermRecordMapper.queryTermRecord(termCode);
    }

    public DLTTermRecord queryLatestTermRecord(){   return idltTermRecordMapper.queryLatestTermRecord(); }
}
