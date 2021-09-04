package com.xiaoyong.hrm.purchase.common;

/**
 * @ClassName StockChangeType
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/28 12:12
 * @Version V1.0
 **/
public enum StockChangeType {

    ORDER("客户下单"),
    PURCHASE("采购进货"),
    MANUAL("人为调整");

    StockChangeType(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
