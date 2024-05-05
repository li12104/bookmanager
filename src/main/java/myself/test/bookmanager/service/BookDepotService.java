package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookDepot;

import java.util.List;

public interface BookDepotService extends IService<BookDepot> {

    /**
     * 获取所有书籍信息,分页
     * @return
     */
    List<BookDepot> bookGetAll(int current,int size);

    /**
     * 通过书名查找,分页
     * @param bookName
     * @return
     */
    List<BookDepot> bookGetByBookName(String bookName,int current,int size);

    /**
     * 新增书籍
     * @param book
     * @return
     */
    Boolean bookInsert(BookDepot book) ;

    /**
     * 通过书名删除书籍
     * @param bookName
     * @return
     */
    Boolean bookDeleteByName(String bookName);

    /**
     * 更改书籍信息
     * @param book
     * @return
     */
    Boolean bookUpdate(BookDepot book);


}
