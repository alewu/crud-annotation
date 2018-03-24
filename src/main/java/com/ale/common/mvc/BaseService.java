package com.ale.common.mvc;

/**
 * @author alewu
 * @date 2017-12-19
 * @description 基础接口
 */
public interface BaseService<T> {

    void insert(T t);

    void delete(T t);

    void update(T t);

    T get(Object obj);



}