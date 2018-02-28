package com.ale.common.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * 分页类的封装
 */
@Data
public class PageBean<E> implements Serializable {

    /*总记录数*/
    private long recordCount;

    /*总页数*/
    private int pageCount;

    /*每页显示数据的集合*/
    private List<E> list;

    public PageBean() {

    }

    public PageBean(List<E> list) {
        this.list = list;
    }

}