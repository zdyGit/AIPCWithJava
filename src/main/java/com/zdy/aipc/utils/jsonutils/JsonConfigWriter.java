package com.zdy.aipc.utils.jsonutils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class JsonConfigWriter {
    public static void writeFile(String filePath,String text){
        FileWriter fw;
        if(text.trim().isEmpty()){
            return ;
        }
        text = JsonStrFormat.format(text);
        try {
            String rootPath = JsonConfigWriter.class.getResource("/").getFile().toString();
            System.out.println("rootPath:"+rootPath);
            String fullFilePath = rootPath+"/"+filePath;
            System.out.println("fullFilePath:"+fullFilePath);
            try {
                fw = new FileWriter(fullFilePath,false);
            }
            catch (IOException ex){
                ex.printStackTrace();
                return;
            }

            PrintWriter out = new PrintWriter(fw);
            out.write(text);
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
    }
}
