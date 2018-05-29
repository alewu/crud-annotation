package com.ale.common.util.third;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
  * @author alewu
  * @date 2018/2/8
  * @description 分页工具类
  */
public class PageUtil {

    public static void startPage(PageParam pageParam){
        Integer number = pageParam.getPageNum();
        Integer size = pageParam.getPageSize();
        Integer  pageNum = number == null ? 1 : number;
        Integer pageSize = size == null ? 20 : size;
        PageHelper.startPage(pageNum, pageSize);
    }

    public static  <E> PageBean<E> getPageBean(List<E> list ){
        PageBean<E> pageBean = new PageBean<>();
        PageInfo<E> pageInfo = new PageInfo<>(list);
        pageBean.setRecordCount(pageInfo.getTotal());
        pageBean.setPageCount(pageInfo.getPages());
        pageBean.setList(list);
        return pageBean;
    }

}
