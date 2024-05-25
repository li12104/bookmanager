package myself.test.bookmanager.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice//声明这个类是用来处理异常的
public class ProjectExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result notApiFoundException(HttpRequestMethodNotSupportedException e) {
        return new Result(Code.METHOD_NOTFOUND, "未找到该路径的方法!");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result notResourceFoundException(NoResourceFoundException e) {
        return new Result(Code.RESOURCE_NOTFOUND, "未找到该路径的资源!");
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        //记录日志
        return new Result(Code.SYSTEM_ERROR, e.getMessage(),"系统繁忙，请稍后再试");
    }
}
