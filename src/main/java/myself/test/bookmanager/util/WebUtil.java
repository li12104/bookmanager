package myself.test.bookmanager.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import myself.test.bookmanager.controller.Code;
import myself.test.bookmanager.controller.Result;

import java.io.PrintWriter;

public class WebUtil {

    public static void sendError(HttpServletResponse response,int code,String msg) throws Exception{
        try (PrintWriter writer = response.getWriter()) {
            ObjectMapper mapper = new ObjectMapper(); // 创建ObjectMapper实例
            Result result = new Result(code, msg); // 创建Result对象
            String jsonString = mapper.writeValueAsString(result); // 将Result对象转换为JSON字符串
            writer.write(jsonString); // 将JSON字符串写入响应
            writer.flush(); // 刷新缓冲区，确保数据被发送
        }
    }
}
