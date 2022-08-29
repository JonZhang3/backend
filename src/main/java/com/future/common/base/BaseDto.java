package com.future.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {

    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        if(page == null || page <= 0) {
            return 1;
        }
        return page;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize <= 0) {
            return 30;
        }
        return pageSize;
    }
}
