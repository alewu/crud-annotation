package com.ale.util.freemarker.eolinker;

import lombok.Data;

@Data
public class BaseInfo {
    /**
     * apiName : 创建商铺
     * apiURI : test.ale.com:9090/yzcx/system/malls
     * apiProtocol : 0
     * apiSuccessMock :
     * apiFailureMock :
     * apiRequestType : 0
     * apiStatus : 0
     * starred : 1
     * apiNoteType : 0
     * apiNoteRaw :
     * apiNote :
     * apiRequestParamType : 0
     * apiRequestRaw :
     * apiUpdateTime : 2017-11-21 15:26:13
     */

    private String apiName;
    private String apiURI;
    private int apiProtocol;
    private String apiSuccessMock;
    private String apiFailureMock;
    private int apiRequestType;
    private int apiStatus;
    private int starred;
    private int apiNoteType;
    private String apiNoteRaw;
    private String apiNote;
    private int apiRequestParamType;
    private String apiRequestRaw;
    private String apiUpdateTime;

}

