package com.xzit.rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    private Result(){
    }
    /*
    * 增加、删除、修改操作成功
    */
    public static <T> Result<T> success(){
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS).setMessage("操作成功");
    }
    public static <T> Result<T> success(T data){
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS)
                .setMessage("操作成功")
                .setData(data);
    }
    /*
     * 增加、删除、修改操作失败
     */
    public static <T> Result<T> fail(){
        return new Result<T>().setSuccess(false)
                .setCode(ResultCode.ERROR)
                .setMessage("操作失败");
    }
}
