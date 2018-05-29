package com.ale.common.util.freemarker;

import lombok.Data;

import java.util.List;
/**
  * @author alewu
  * @date 2018/4/6
  * @description 表的元数据
  */
@Data
public class TableMetaData {
    /** 数据库表名 **/
    private String tableName;
    /** 数据库表注释 **/
    private String tableRemarks;
    /** 数据库表名对应的实体类名 **/
    private String entityName;
    /** 列对象 **/
    private List<CustomField> customFields;

}
