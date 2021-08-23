package com.xiaoyong.hrm.analyzer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomerStatusStatVO
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/23 22:05
 * @Version V1.0
 **/
public class CustomerStatusStatVO {

    private List<CustomerStatusEntry> entryList = new ArrayList<>();

    public List<CustomerStatusEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<CustomerStatusEntry> entryList) {
        this.entryList = entryList;
    }

    static public class CustomerStatusEntry{
        private String name;
        private long value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

}
