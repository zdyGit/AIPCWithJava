package com.zdy.aipc.utils.jsonutils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonConfigWriter {
    public static void writeFile(String filePath,String text){
        FileWriter fw;
        if(text.trim().isEmpty()){
            return ;
        }
        text = JsonStrFormat.format(text);
        try {
            String fileFullPath = JsonConfigReader.class.getClassLoader().getResource(filePath).getPath();
            try {
                fw = new FileWriter(fileFullPath,false);
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
