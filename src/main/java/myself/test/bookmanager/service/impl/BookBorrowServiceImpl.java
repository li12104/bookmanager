package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.dao.BookBorrowDao;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.BookBorrow;
import myself.test.bookmanager.service.BookBorrowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookBorrowServiceImpl extends ServiceImpl<BookBorrowDao, BookBorrow> implements BookBorrowService {
    @Override
    @Transactional
    public Boolean bookChange(String username, String bookName, int type) {
        try {
            int num = getBaseMapper().insert(new BookBorrow(0, username, bookName, type));
            return num > 0;
        } catch (RuntimeException e) {
            throw new ServiceException("用户名或书籍信息有误", e);
        }
    }


    @Override
    public List<BookBorrow> bookLog(String username, int current, int size) {
        LambdaQueryWrapper<BookBorrow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BookBorrow::getUName, username);
        IPage p = new Page(current, size);
        IPage page = getBaseMapper().selectPage(p, lqw);
        return page.getRecords();
    }
}
