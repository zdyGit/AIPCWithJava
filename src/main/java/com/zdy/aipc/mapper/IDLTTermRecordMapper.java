package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.DLTTermRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : IDLTTermRecordMapper
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 11:19
 */
@Mapper
@Component
public interface IDLTTermRecordMapper {

    void addTermRecord(DLTTermRecord dltTermRecord);

    DLTTermRecord queryTermRecord(String termCode);

    DLTTermRecord queryLatestTermRecord();

    List<DLTTermRecord> queryLimitTermRecord(String termCode,int limitNum);
}
