package com.ale.common.util.eolinker;

import lombok.Data;

import java.util.List;
@Data
public class RequestInfo {
    /**
     * paramNotNull : 0
     * paramType : 0
     * paramName : 商铺名称
     * paramKey : mallName
     * paramValue :
     * paramLimit :
     * paramNote :
     * paramValueList : []
     * default : 0
     * $index : 0
     */
    private String paramNotNull;
    private String paramType;
    private String paramName;
    private String paramKey;
    private String paramValue;
    private String paramLimit;
    private String paramNote;
    private int defaultX;
    private int $index;
    private List<?> paramValueList;

}
