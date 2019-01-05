package com.ale.util.freemarker.bean;

import lombok.Data;

import java.util.List;

@Data
public class TableMetaData {
    /** 数据库表名 **/
    private String tableName;
    /** 数据库表名对应的实体类名 **/
    private String entityName;
    /** 列对象 **/
    private List<CustomField> customFields;

}
