package com.zdy.aipc.utils;

import com.zdy.aipc.utils.jsonutils.JsonConfigReader;
import org.junit.Test;

public class ConfigReaderTest {

    @Test
    public void readTest(){
        String filePath = "pbif.json";

        Object jdata = null ;
        try {
            jdata = JsonConfigReader.getJsonObject(filePath);
        }
        catch (Exception ex)
        {
            System.out.println(String.format("读取配置文件%s错误，%s",filePath,ex.toString()));
        }
    }
}
