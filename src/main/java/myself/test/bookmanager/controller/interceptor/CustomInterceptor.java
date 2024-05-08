package myself.test.bookmanager.controller.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myself.test.bookmanager.controller.Code;
import myself.test.bookmanager.controller.Result;
import myself.test.bookmanager.util.JwtToken;
import myself.test.bookmanager.util.UserInfo;
import myself.test.bookmanager.util.WebUtil;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;

public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);//设置返回数据为json格式
        if (token == null || token.isEmpty()) {
            // 如果没有提供token，返回未授权错误
            WebUtil.sendError(response,Code.TOKEN_NOTFOUND,"请在请求头中添加token信息");
            return false;
        }
        boolean verify = JwtToken.verify(token);//校验登录信息是否过期
        if (verify){
            try {
                response.setHeader("Access-Control-Allow-Origin","*");
                String username=JwtToken.getUsername(token);//获取用户名
                UserInfo.getInstance().setUsername(username);//将用户名存入单例类中
                return true;
            }catch (JWTVerificationException e){
                WebUtil.sendError(response,Code.TOKEN_ISLATE,"token解析错误，请请确保正确传入token");
                return false;
            }
        }else {
            WebUtil.sendError(response,Code.TOKEN_ISLATE,"认证失败,登录信息已失效,请重新登录");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
