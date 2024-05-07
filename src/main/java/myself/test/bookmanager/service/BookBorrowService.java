package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookBorrow;

import java.util.List;

public interface BookBorrowService extends IService<BookBorrow> {

    /**
     * 书籍借阅的变更
     * @param username
     * @param bookName
     * @param type
     * @return
     */
    Boolean bookChange(String username,String bookName,int type);


    /**
     * 查询用户所有借阅记录
     * @param current
     * @param size
     * @return
     */
    List<BookBorrow> bookLog(int current,int size);
}
