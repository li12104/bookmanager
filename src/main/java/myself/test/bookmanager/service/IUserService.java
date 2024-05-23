package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.dto.UserDTO;
import myself.test.bookmanager.model.user.*;

import java.util.List;

public interface IUserService extends IService<User> {
    /**
     * 通过账号密码登录
     */
    UserDTO login(Login login);

    /**
     * 注册账号
     */

    Boolean register(Register register);

    /**
     * 获取所有非管理员账户
     */
    List<UserDTO> getAllUser();

    /**
     * 修改用户信息
     */
    boolean updateUser(UpUser upUser);


    /**
     * 通过用户id删除
     */
    boolean deleteUserById(int id);
}
