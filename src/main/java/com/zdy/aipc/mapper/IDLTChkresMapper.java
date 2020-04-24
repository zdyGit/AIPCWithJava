package com.zdy.aipc.mapper;

import com.zdy.aipc.domain.DLTChkres;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : IDLTChkresMapper
 * @Description :
 * @Author : dy
 * @Date: 2020-04-23 21:09
 */
@Mapper
@Component
public interface IDLTChkresMapper {

    void addChkres(DLTChkres dltChkres);

    DLTChkres queryChkres(String ticketno ,String term);

    List<DLTChkres> queryChkresByticketno(String ticketno);
}
