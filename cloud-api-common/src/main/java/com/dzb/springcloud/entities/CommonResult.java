package com.dzb.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ding ZhenBei
 * @data 2020/3/10 20:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
