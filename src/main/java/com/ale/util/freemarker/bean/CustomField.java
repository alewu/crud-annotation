package com.ale.util.freemarker.bean;

import lombok.Data;

/**
 * @author alewu
 * @date 2017/11/4 0:37
 * @description 数据库字段封装类
 */
@Data
public class CustomField {
    /** 数据库字段名称 */
    private String columnName;
    /** 数据库字段名对应的实体类成员变量名 */
    private String memberVariable;
    /** 数据库字段类型 */
    private String typeName;
    /** 数据库字段注释 */
    private String remarks;

}
