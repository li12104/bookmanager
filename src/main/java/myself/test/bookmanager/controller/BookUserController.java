package myself.test.bookmanager.controller;

import myself.test.bookmanager.model.BookUser;
import myself.test.bookmanager.service.BookUserService;
import myself.test.bookmanager.util.JwtToken;
import myself.test.bookmanager.util.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class BookUserController {

    @Autowired
    private BookUserService bookUserService;


    @PostMapping("/login")
    public Result login(@RequestBody BookUser user)  {
        BookUser resultUser = bookUserService.login(user.getUsername(), user.getPassword());
        if (resultUser == null) {
            return new Result(Code.SERVICE_ERROR, "账号或密码错误");
        } else {
            String token = JwtToken.generateToken(resultUser.getUsername(), resultUser.getIsAdmin());
            String role = resultUser.getIsAdmin() == 0 ? "管理员" : "用户"; // 0是非管理员，1是管理员
            return new Result(Code.SERVICE_OK, token, role); // 返回token和角色信息
        }
    }

    @PostMapping("/register")
    private Result register(@RequestBody BookUser user){
        try {
            Boolean isSuccess= bookUserService.register(user);
            if (isSuccess){
                return new Result(Code.SERVICE_OK,"注册成功");
            }else {
                return new Result(Code.SERVICE_ERROR,"注册失败");
            }
        }catch (RuntimeException e){
            return new Result(Code.SERVICE_ERROR,"账号已存在");
        }
    }

}
