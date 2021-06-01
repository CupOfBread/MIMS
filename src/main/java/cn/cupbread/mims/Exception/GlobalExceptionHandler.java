package cn.cupbread.mims.Exception;

import cn.cupbread.mims.Component.RetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/06/01
 * @description :
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public RetResponse RequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new RetResponse().makeErrRsp(100, "请求METHOD不支持:"+e.getCause());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public RetResponse NullPointer(NullPointerException e) {
        return new RetResponse().makeErrRsp(500, "参数缺失或其他错误:" + e.getMessage());
    }

    @ExceptionHandler(value = IOException.class)
    public RetResponse IO(IOException e) {
        return new RetResponse().makeErrRsp(500, "IO错误:"+e.getCause());
    }

}
