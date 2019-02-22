package com.zdy.aipc.utils;

import com.alibaba.fastjson.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class ConfigReader  {

    public static JSONObject configReaderToJson(String filePath) throws IOException{
        try {
            String fileFullPath = ConfigReader.class.getClassLoader().getResource(filePath).getPath();
            File file = new File(fileFullPath);
            String jsonData = jsonRead(file);
            JSONObject jsonObject = JSON.parseObject(jsonData);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray configReaderToJsonArry(String filePath) throws IOException{
        try {
            String fileFullPath = ConfigReader.class.getClassLoader().getResource(filePath).getPath();
            File file = new File(fileFullPath);
            String jsonData = jsonRead(file);
            JSONArray jsonObject = JSONArray.parseArray(jsonData);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}