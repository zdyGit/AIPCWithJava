package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.DLTTermDetail;
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
public interface IDLTTermDetailMapper {

    void addTermDetail(DLTTermDetail dltTermDetail);

    List<DLTTermDetail> queryTermDetail(String termCode);
}
