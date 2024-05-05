package myself.test.bookmanager.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//只序列化不为null的
public class Result {
    private Object data;
    private Integer code;
    private String msg;
    public Result(Integer code,Object data,  String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }

}
