package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.dao.BookUserDao;
import myself.test.bookmanager.model.BookUser;
import myself.test.bookmanager.service.BookUserService;
import org.springframework.stereotype.Service;

@Service
public class BookUserServiceImpl extends ServiceImpl<BookUserDao, BookUser> implements BookUserService {

    @Override
    public BookUser login(String username, String password) {
        LambdaQueryWrapper<BookUser> lqw=new LambdaQueryWrapper<>();
        lqw.eq(BookUser::getUsername,username).eq(BookUser::getPassword,password);
        BookUser user=getBaseMapper().selectOne(lqw);
        return user;
    }
    @Override
    public Boolean register(BookUser user) {
        int insert = getBaseMapper().insert(user);
        return insert>0;
    }

}
