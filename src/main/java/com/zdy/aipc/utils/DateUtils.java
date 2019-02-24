package com.zdy.aipc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    private  static SimpleDateFormat strandDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static int getWorkDaysDiff(String dayStr1,String dayStr2) throws ParseException {
        Date day1;
        Date day2;

        day1 = dateFormat.parse(dayStr1);
        day2 = dateFormat.parse(dayStr2);

        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(day1);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(day2);

        //如果前者日期比后者大，交换顺序
        if(calendar1.getTimeInMillis()>calendar2.getTimeInMillis()){
            Calendar var1= new GregorianCalendar();
            var1 = calendar1;
            calendar1 = calendar2;
            calendar2 = var1;
        }

        int count = 0;
        while(true){
            if(calendar1.getTimeInMillis() != calendar2.getTimeInMillis()){
                int week_of_days = calendar1.get(Calendar.DAY_OF_WEEK);
                calendar1.add(Calendar.DATE,1);
                if(week_of_days !=1 && week_of_days !=7){
                    count = count+1;
                }
            }
            else{
                break;
            }
        }
        return count;

    }

    public static int getDaysDiff(String dayStr1,String dayStr2) throws ParseException {
        Date day1;
        Date day2;

        day1 = dateFormat.parse(dayStr1);
        day2 = dateFormat.parse(dayStr2);

        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(day1);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(day2);

        //如果前者日期比后者大，交换顺序
        if(calendar1.getTimeInMillis()>calendar2.getTimeInMillis()){
            Calendar var1= new GregorianCalendar();
            var1 = calendar1;
            calendar1 = calendar2;
            calendar2 = var1;
        }

        int count = 0;
        while(true){
            if(calendar1.getTimeInMillis() != calendar2.getTimeInMillis()){
                calendar1.add(Calendar.DATE,1);
                count = count + 1;
            }
            else{
                break;
            }
        }
        return count;

    }
}
