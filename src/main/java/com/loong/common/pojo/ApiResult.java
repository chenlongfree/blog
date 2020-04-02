package com.loong.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
    private boolean success;
    private Integer code;
    private String msg;
    private Object data;
    private long count;

    public static ApiResult createSuccess(long count, Object data){
        ApiResult result = new ApiResult(true, 0, "", data, count);
        return result;
    }

    public static ApiResult createSuccess(Object data){
        ApiResult result = createSuccess(1, data);
        return result;
    }

    public static ApiResult createSuccess(){
        ApiResult result = createSuccess(null);
        return result;
    }

    public static ApiResult createFaild(String msg){
        ApiResult result = new ApiResult(false, 400, msg, null, 0);
        return result;
    }
}
