package com.zdy.aipc.utils.jsonutils;

import com.alibaba.fastjson.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class JsonConfigReader {

    public static JSONObject getJsonObject(String filePath) throws IOException{
        try {
            String jsonDataStr = jsonReadByStream(filePath);
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
            String jsonDataStr = jsonReadByStream(filePath);
            JSONArray jsonArray = JSONArray.parseArray(jsonDataStr);
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String jsonReadByStream(String filePath) throws Exception{
        InputStream inputStream = JsonConfigReader.class.getClassLoader().getResourceAsStream(filePath);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    private static String jsonRead(String filePath){
        String fileFullPath = JsonConfigReader.class.getClassLoader().getResource(filePath).getPath();
        System.out.println("load file:"+fileFullPath);
        File file = new File(fileFullPath);
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