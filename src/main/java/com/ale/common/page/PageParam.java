package com.ale.common.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @author alewu
 * @date 2017/10/18 20:50
 * @description 分页参数类
 */
@Data
public class PageParam implements Serializable {
    /*页码*/
    private Integer pageNum;
    /*每页显示记录数*/
    private Integer pageSize;

}
