package yumefusaka.galgamesite.handler;

// handler/GlobalExceptionHandler.java

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yumefusaka.galgamesite.common.result.Result;

@RestControllerAdvice(basePackages = {"yumefusaka.galgamesite.controller.user", "yumefusaka.galgamesite.controller.activity"},annotations = {RestController.class})
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler(Exception.class) //指定能够处理的异常类型
    public Result ex(Exception e){
        e.printStackTrace();//打印堆栈中的异常信息
        //捕获到异常之后，响应一个标准的Result
        return Result.error(e.getMessage());
    }
}