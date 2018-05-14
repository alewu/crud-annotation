package com.ale.common.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author alewu
 * @date 2018/4/15
 * @description 统一响应结构
 */
@Data
@Builder
public class ApiResponse<U, S> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private U message;
    /**
     * 数据
     */
    private S data;
}
