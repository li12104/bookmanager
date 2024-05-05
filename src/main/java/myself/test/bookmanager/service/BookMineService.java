package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookDepot;
import myself.test.bookmanager.model.BookMine;

import java.util.List;
import java.util.Map;

public interface BookMineService extends IService<BookMine> {

    /**
     * 借阅书籍
     * @param bookName
     * @return
     */
    Boolean bookBorrow(String bookName);

    /**
     * 归还书籍
     * @param bookName
     * @return
     */
    Boolean bookReturn(String bookName);

    /**
     * 获取所有借阅书籍
     * @return
     */
    List<BookDepot> bookAllBorrow(int current,int size);
}
