package com.zdy.aipc.domain.productconfig;

public class ProductConfigFactory {

    public static IProductConfig getProductConfig(String prodCode){
        IProductConfig productConfig ;
        prodCode = prodCode.trim().toLowerCase();
        switch (prodCode){
            case "a1":
                productConfig = new CYBProductConfig();
                break;
            case "a2":
                productConfig = new SZProductConfig();
                break;
            default:
                productConfig = null;
                break;
        }
        return productConfig;
    }
}
