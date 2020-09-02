package com.netposa.template.model;

import lombok.Data;

import java.util.List;

/**
 * Created by LP on 2019-03-01.
 */
@Data
public class PageResult<T> {
    private List<T> list;
    private long total;

    public PageResult(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult() {
    }
}
