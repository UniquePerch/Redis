package com.example.entity.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "数据相应实体封装类")
public class RestBean<E> {
    @ApiModelProperty("状态码")
    int code;
    @ApiModelProperty("状态码信息")
    String reason;
    @ApiModelProperty("响应实体信息")
    E data;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
