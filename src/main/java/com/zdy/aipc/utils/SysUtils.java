package com.zdy.aipc.utils;

public class SysUtils {
    private static String rootPath = new String();

    static{
        SysUtils.rootPath = SysUtils.class.getResource("/").getPath();
    }

    public static String getSysRootPath(){
        String path = SysUtils.rootPath.trim();
        if(path.length() == 0)
            return "";
        if(!path.endsWith("/")){
            path = path+"/";
            return path;
        }
        return path;
    }

    public static void setSysRootPath(String path){
        path = path.trim();
        if(path.isEmpty()){
            return ;
        }
        SysUtils.rootPath = path;
    }


}
