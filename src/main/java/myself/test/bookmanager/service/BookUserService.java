package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookUser;

public interface BookUserService extends IService<BookUser> {
    /**
     * 通过账号密码登录
     * @param username
     * @param password
     * @return
     */
    BookUser login(String username, String password);

    Boolean register(BookUser user);
}
