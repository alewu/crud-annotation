package com.ale.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    /** 状态码 */
    private int code;
    /** 数据 */
    private T data;
}
