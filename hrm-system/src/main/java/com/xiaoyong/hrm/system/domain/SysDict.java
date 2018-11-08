package com.xiaoyong.hrm.system.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统字典项信息
 * @ClassName SysDict
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 21:32
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_sys_dict")
public class SysDict extends BaseEntity {

    //字典类型
    private String dictType;
    //字典标签
    private String dictLabel;
    //字典值
    private String dictValue;

    @Column(name = "dict_type", length = 50, nullable = false)
    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    @Column(name = "dict_label", length = 50, nullable = false)
    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    @Column(name = "dict_value", length = 50, nullable = false)
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}
