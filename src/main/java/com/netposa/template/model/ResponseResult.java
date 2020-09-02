package com.netposa.template.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param <T>
 * @ClassName: ResponseResult
 * @Description: 接口返回信息实体
 */
@ApiModel(value = "接口返回信息实体", description = "接口请求返回信息")
@Data
public class ResponseResult<T> {

    @ApiModelProperty(value = "请求结果编码")
    private int code = 200;

    @ApiModelProperty(value = "请求结果信息")
    private String message = "操作成功";

    @ApiModelProperty(value = "请求结果数据内容")
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResponseResult(T data) {
        this.data = data;
    }


}
