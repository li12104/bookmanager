package myself.test.bookmanager.controller.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myself.test.bookmanager.controller.Code;
import myself.test.bookmanager.util.JwtToken;
import myself.test.bookmanager.util.WebUtil;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CustomInterceptor implements HandlerInterceptor {

    private boolean checkPermission(HttpServletRequest request, HttpServletResponse response, String token) throws Exception{
        //判断是否需要验证管理权限
        String url=request.getRequestURI();
        String method=request.getMethod();
        //是否为留言操作
        if (url.contains("comment")){
            //只有删除留言操作需要进行权限验证
            if (method.equals("DELETE")){
                //是管理员直接通过
                if (JwtToken.isAdmin(token)){
                    return true;
                }
                WebUtil.sendError(response, Code.NOT_QX, "该用户权限等级不够！");
                return false;
            }
            return true;
        }
        //不是书架操作都需要判断权限
        if (!url.contains("bookshelf")){
            //该请求是否修改数据行为
            if (!method.equals("GET")){
                //是管理员直接通过
                if (JwtToken.isAdmin(token)){
                    return true;
                }
                WebUtil.sendError(response, Code.NOT_QX, "该用户权限等级不够！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);//设置返回数据为json格式
        if (token == null || token.isEmpty()) {
            // 如果没有提供token，返回未授权错误
            WebUtil.sendError(response, Code.TOKEN_NOTFOUND, "请在请求头中添加token信息");
            return false;
        }
        //校验登录信息是否过期
        if (JwtToken.verify(token)) {
            WebUtil.sendError(response, Code.TOKEN_ISLATE, "登录信息已过期");
            return false;
        }
        return checkPermission(request,response,token);
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
