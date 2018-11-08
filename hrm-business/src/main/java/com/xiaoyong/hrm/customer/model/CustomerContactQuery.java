package com.xiaoyong.hrm.customer.model;/**
 * Created by atlantisholic on 2018/8/22.
 */

/**
 * @ClassName CustomerContactQuery
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/22 21:19
 * @Version 1.0.0
 **/
public class CustomerContactQuery {

    private String keyword;
    private String status;
    private Integer productId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
