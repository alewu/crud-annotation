package com.ale.common.mvc;

/**
 * @author alewu
 * @date 2017-12-19
 * @description 基础接口
 */
public interface BaseService<T> {

    int saveOne(T t);

    int removeOne(String id);

    int updateOne(T t);

    T getOne(Object obj);



}