package com.zdy.aipc.utils;

public class MathUtils {
    public static double LimitPoint(double num ,int n){
        double m = (double)Math.round(num*Math.pow(10,n));
        return m/Math.pow(10,n);
    }
}
