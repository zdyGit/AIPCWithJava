package com.zdy.aipc.utils;

import org.assertj.core.util.DateUtil;
import org.junit.Test;

public class WorkDateComputerTest {

    @Test
    public void getDateDiff() throws Exception{
        String day1 = "20190218";
        String day2 = "20190225";
        int count = DateUtils.getWorkDaysDiff(day1,day2);
        System.out.println(count);
    }

    @Test
    public void getDateDiff1() throws Exception{
        String day1 = "20191231";
        String day2 = "20190101";
        int count = DateUtils.getDaysDiff(day1,day2);
        System.out.println(count);
    }
}
