package com.zdy.aipc.utils.jsonutils;

import com.zdy.aipc.utils.SysUtils;

import java.io.*;

public class JsonConfigWriter {
    public static void writeFile(String filePath,String text){
        BufferedWriter fw;
        if(text.trim().isEmpty()){
            return ;
        }
        text = JsonStrFormat.format(text);
        try {
            String rootPath = SysUtils.getSysRootPath();
            String fullFilePath = rootPath+filePath;
            System.out.println("JsonConfigWriter.writeFile.jsonfilepath:"+fullFilePath);
            try {
                //fw = new FileWriter(fullFilePath,false);
                fw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream(fullFilePath,false),"UTF-8"));
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
