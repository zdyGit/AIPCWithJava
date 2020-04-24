package com.zdy.aipc.utils.jsonutils;

import com.alibaba.fastjson.*;
import com.zdy.aipc.utils.LogbackUtils;
import com.zdy.aipc.utils.SysUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class JsonConfigReader {

    public static JSONObject getJsonObject(String filePath) throws IOException{
        try {
            String jsonDataStr = jsonRead(filePath);
            System.out.println(jsonDataStr);
            JSONObject jsonObject = JSON.parseObject(jsonDataStr);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getJsonArray(String filePath) throws IOException{
        try {
            String jsonDataStr = jsonRead(filePath);
            JSONArray jsonArray = JSONArray.parseArray(jsonDataStr);
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //读取Jar包内配置文件
    private static String jsonReadByStream(String filePath) throws Exception{
        String rootPath = SysUtils.getSysRootPath();
        String fullFilePath = rootPath+filePath;
        System.out.println("JsonConfigReader.jsonReadByStream.jsonfilepath:"+fullFilePath);
        InputStream inputStream = JsonConfigReader.class.getResourceAsStream(fullFilePath);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    //读取jar包外配置文件
    private static String jsonRead(String filePath){
        String rootPath = SysUtils.getSysRootPath();
        String fullFilePath = rootPath+filePath;
        LogbackUtils.info(String.format("加载配置文件 %s",fullFilePath));
        System.out.println("JsonConfigReader.jsonRead.jsonfilepath:"+fullFilePath);
        File file = new File(fullFilePath);
        return jsonRead(file);
    }

    private static String jsonRead(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}