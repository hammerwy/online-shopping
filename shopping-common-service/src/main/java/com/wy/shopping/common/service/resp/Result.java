package com.wy.shopping.common.service.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wy.shopping.common.service.exception.BizException;
import com.wy.shopping.common.service.exception.ExceptionCodeEnum;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /** 执行结果 */
    private boolean isSuccess;

    /** 错误码 */
    private String errorCode;

    /** 错误原因 */
    private String message;

    /** 返回数据 */
    private T data;

    /**
     * 返回成功的结果
     * @param data 需返回的结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(T data){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        result.data = data;
        return result;
    }

    /**
     * 返回成功的结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        return result;
    }

    /**
     * 返回失败的结果
     * PS：返回"未知异常"
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult(){
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = ExceptionCodeEnum.UNKNOW_ERROR.getCode();
        result.message = ExceptionCodeEnum.UNKNOW_ERROR.getMessage();
        return result;
    }

    /**
     * 返回失败的结果
     * @param bizExcepiton 异常
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult(BizException bizExcepiton){
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = bizExcepiton.getExceptionCode().getCode();
        result.message = bizExcepiton.getExceptionCode().getMessage();
        return result;
    }

    /**
     * 返回失败的结果
     * @param bizExcepiton 异常
     * @param data 需返回的数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult(BizException bizExcepiton, T data){
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = bizExcepiton.getExceptionCode().getCode();
        result.message = bizExcepiton.getExceptionCode().getMessage();
        result.data = data;
        return result;
    }
}
