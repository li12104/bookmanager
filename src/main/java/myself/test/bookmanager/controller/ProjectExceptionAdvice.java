package myself.test.bookmanager.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//声明这个类是用来处理异常的
public class ProjectExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result notFoundException(HttpRequestMethodNotSupportedException e){
        return new Result(Code.METHOD_NOTFOUND,"未找到该路径的资源或方法");
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e){
        e.printStackTrace();
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(Code.SYSTEM_ERROR,"系统繁忙，请稍后再试");
    }
}
