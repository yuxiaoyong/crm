package com.xiaoyong.hrm.purchase.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName StockQuery
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/28 22:58
 * @Version V1.0
 **/
public class StockQuery {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String keyword;
    private Integer productId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
