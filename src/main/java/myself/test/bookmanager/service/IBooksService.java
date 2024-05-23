package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.Books;
import myself.test.bookmanager.model.vo.BooksVO;

import java.util.List;

public interface IBooksService extends IService<Books> {

    /**
     * 不传入参数则表示查询全部否则只查询书籍数量大于0的
     * @param onlyHave
     * @return
     */
    List<Books> getAllBooks(Integer onlyHave);

    /**
     * 通过书名查询，其余与上个方法大致相同
     * @param name
     * @param onlyHave
     * @return
     */
    List<Books> getBooks(String name,Integer onlyHave);

    /**'
     * 获取指定书籍
     */
    BooksVO getBook(int id);

    /**
     * 新增书籍
     */
    boolean saveBooks(Books books);

    /**
     * 修改书籍信息
     */
    boolean updateBooks(Books books);

    /**
     * 删除书籍根据id
     */
    boolean deleteBooksById(int id);

}
