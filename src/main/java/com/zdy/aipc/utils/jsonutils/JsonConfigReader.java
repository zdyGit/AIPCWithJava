package com.zdy.aipc.utils.jsonutils;

import com.alibaba.fastjson.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class JsonConfigReader {

    public static JSONObject getJsonObject(String filePath) throws IOException{
        try {
            String jsonDataStr = jsonRead(filePath);
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

    private static String jsonRead(String filePath){
        String fileFullPath = JsonConfigReader.class.getClassLoader().getResource(filePath).getPath();
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
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}