package com.ale.util.freemarker.eolinker;

import lombok.Data;

import java.util.List;
@Data
public class EoLinker {

    /**
     * baseInfo : {"apiName":"创建商铺","apiURI":"test.ale.com:9090/yzcx/system/malls","apiProtocol":0,"apiSuccessMock":"","apiFailureMock":"","apiRequestType":0,"apiStatus":0,"starred":1,"apiNoteType":0,"apiNoteRaw":"","apiNote":"","apiRequestParamType":0,"apiRequestRaw":"","apiUpdateTime":"2017-11-21 15:26:13"}
     * headerInfo : []
     * requestInfo : [{"paramNotNull":"0","paramType":"0","paramName":"商铺名称","paramKey":"mallName","paramValue":"","paramLimit":"","paramNote":"","paramValueList":[],"default":0,"$index":0},{"paramNotNull":"0","paramType":"0","paramName":"商铺铺主名","paramKey":"mallOwnerName","paramValue":"","paramLimit":"","paramNote":"","paramValueList":[],"default":0,"$index":1},{"paramNotNull":"0","paramType":"0","paramName":"商铺铺主手机号","paramKey":"mallOwnerPhone","paramValue":"","paramLimit":"","paramNote":"","paramValueList":[],"default":0,"$index":2}]
     * resultInfo : []
     * mockInfo : {"rule":[{"paramKey":"","paramType":"0","$index":0}],"result":"{}","mockConfig":{"rule":"","type":"object"}}
     */

    private BaseInfo baseInfo;
    private MockInfo mockInfo;
    private List<?> headerInfo;
    private List<BaseInfo> requestInfo;
    private List<?> resultInfo;





   
}
