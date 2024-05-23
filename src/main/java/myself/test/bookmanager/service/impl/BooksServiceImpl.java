package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.mapper.BooksMapper;
import myself.test.bookmanager.model.Books;
import myself.test.bookmanager.model.vo.BooksVO;
import myself.test.bookmanager.service.IBooksService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {
    @Override
    public List<Books> getAllBooks(Integer onlyHave) {
        return lambdaQuery().gt(onlyHave!=null,Books::getNumber,0).list();
    }

    @Override
    public List<Books> getBooks(String name, Integer onlyHave) {
        return lambdaQuery().gt(onlyHave!=null,Books::getNumber,0)
                .like(Books::getTitle,name).list();
    }

    @Override
    public BooksVO getBook(int id) {
        Books byId = this.getById(id);
        if (byId!=null){
            BooksVO booksVO=new BooksVO();
            BeanUtils.copyProperties(byId,booksVO);
            return booksVO;
        }
        return null;
    }

    @Override
    public boolean saveBooks(Books books) {
        if (books.getNumber()<=0){
            throw new ServiceException("新增书籍数量应大于等于1！",null);
        }
        try {
            boolean success = save(books);
            return success;
        }catch (RuntimeException e){
            throw new ServiceException("书籍已存在！",null);
        }
    }

    @Override
    public boolean updateBooks(Books books) {
        try {
            return lambdaUpdate().eq(Books::getId,books.getId()).update(books);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ServiceException("不能修改为已存在的书籍!",e);
        }
    }

    @Override
    public boolean deleteBooksById(int id) {
        return removeById(id);
    }
}
