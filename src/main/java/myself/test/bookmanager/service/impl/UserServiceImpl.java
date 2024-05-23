package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.enums.UserAdmin;
import myself.test.bookmanager.mapper.UserMapper;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.dto.UserDTO;
import myself.test.bookmanager.model.user.*;
import myself.test.bookmanager.service.IUserService;
import myself.test.bookmanager.util.MineMd5;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(Login login) {
        return UserDTO.of(lambdaQuery().eq(User::getUsername,login.getUsername()).eq(User::getPassword,MineMd5.pwdMd(login.getPwd()))
                .one());
    }

    @Override
    public Boolean register(Register register) {
        try {
            User user = new User(0, register.getUsername(), MineMd5.pwdMd(register
                    .getPwd()), UserAdmin.USER, register.getNickname(),null);
            int insert = getBaseMapper().insert(user);
            return insert > 0;
        } catch (RuntimeException e) {
            throw new ServiceException("账号已存在!", e);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users=lambdaQuery().eq(User::getIsAdmin,UserAdmin.USER).list();
        return users.stream().map(user -> UserDTO.of(user)).toList();
    }

    @Override
    public boolean updateUser(UpUser upUser) {
        if (upUser.getPwd().equals(upUser.getNewPwd())){
            throw new ServiceException("新密码不能与原密码相同!",null);
        }
        User exist=lambdaQuery().eq(User::getId,upUser.getId()).eq(User::getPassword,MineMd5.pwdMd(upUser.getPwd())).one();
        if (exist==null){
            throw new ServiceException("账号密码不匹配!",null);
        }
        if (exist.getIsAdmin().getNum()==0){
            throw new ServiceException("不能修改管理员信息!",null);
        }
        User updatedUser = new User();
        updatedUser.setId(upUser.getId());
        updatedUser.setPassword(MineMd5.pwdMd(upUser.getNewPwd()));
        updatedUser.setNickname(upUser.getNickname());
        return lambdaUpdate().eq(User::getId,updatedUser.getId())
                .update(updatedUser);
    }

    @Override
    public boolean deleteUserById(int id) {
        User user=lambdaQuery().eq(User::getId,id).one();
        if (user==null){
            return false;
        }
        if (user.getIsAdmin().getNum()==0){
            throw new ServiceException("无法删除管理员!",null);
        }
        return removeById(id);
    }


}
