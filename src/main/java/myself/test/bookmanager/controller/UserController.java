package myself.test.bookmanager.controller;

import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.dto.UserDTO;
import myself.test.bookmanager.model.user.Login;
import myself.test.bookmanager.model.user.Register;
import myself.test.bookmanager.model.user.UpUser;
import myself.test.bookmanager.service.IUserService;
import myself.test.bookmanager.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService IUserService;


    @PostMapping("/login")
    public Result login(@RequestBody Login login) {
        UserDTO resultUser = IUserService.login(login);
        if (resultUser == null) {
            return new Result(Code.SERVICE_ERROR, "账号或密码错误!");
        }
        String token = JwtToken.generateToken(resultUser.getIsAdmin().getNum());
        return new Result(Code.SERVICE_OK, resultUser, "登录成功!", token); // 返回token和角色信息
    }

    @PostMapping("/register")
    private Result register(@RequestBody Register register) {
        try {
            Boolean isSuccess = IUserService.register(register);
            if (isSuccess) {
                return new Result(Code.SERVICE_OK, "注册成功!");
            }
            return new Result(Code.SERVICE_ERROR, "注册失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @GetMapping
    public Result getAllUser() {
        return new Result(Code.SERVICE_OK, IUserService.getAllUser(), "请求成功!");
    }

    @PutMapping
    public Result updateUser(@RequestBody UpUser user){
        try {
            boolean success= IUserService.updateUser(user);
            if (success){
                return new Result(Code.SERVICE_OK,"修改成功!");
            }
            return new Result(Code.SERVICE_ERROR,"修改失败!");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private Result deleteUserById(@PathVariable int id){
        try {
            boolean success= IUserService.deleteUserById(id);
            if (success){
                return new Result(Code.SERVICE_OK,"删除成功!");
            }
            return new Result(Code.SERVICE_ERROR,"删除失败!");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }
}
