package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookShelf;
import myself.test.bookmanager.model.dto.BookShelfDTO;

import java.util.List;

public interface IBookShelfService extends IService<BookShelf> {

    /**
     * 查询用户书架书籍
     */
    BookShelfDTO getUserBooks(int id);

    /**
     * 操作书架书籍
     */
    boolean booksBorrow(int bookId,int userId);

    boolean booksReturn(int bookId,int userId);
}
