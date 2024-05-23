package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import myself.test.bookmanager.enums.BorrowType;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.mapper.BookShelfMapper;
import myself.test.bookmanager.model.BookShelf;
import myself.test.bookmanager.model.Books;
import myself.test.bookmanager.model.Borrow;
import myself.test.bookmanager.model.dto.BookShelfDTO;
import myself.test.bookmanager.model.user.User;
import myself.test.bookmanager.model.vo.BooksVO;
import myself.test.bookmanager.service.IBookShelfService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class BookShelfServiceImpl extends ServiceImpl<BookShelfMapper, BookShelf> implements IBookShelfService {

    @Override
    public BookShelfDTO getUserBooks(int id) {
        //查询用户书架信息列表
        List<BookShelf> bookShelves = lambdaQuery().eq(BookShelf::getUserId, id).list();
        //如果用户书架中并没有书籍
        if (bookShelves.isEmpty()) {
            //如果未查询到用户
            User user = Db.lambdaQuery(User.class).eq(User::getId, id).one();
            if (user == null) {
                throw new ServiceException("未查询到用户书架书籍信息,请检查用户id是否正确!", null);
            }
            return new BookShelfDTO(user.getId(), Collections.emptyList());
        }
        //将用户书架书籍id提取出来
        List<Integer> ids = bookShelves.stream().map(BookShelf::getBookId).toList();
        //根据书籍id查询书籍信息
        List<Books> books = Db.lambdaQuery(Books.class).in(Books::getId, ids).list();
        //将books中的几个字段提取出来组成新的BooksVO集合
        List<BooksVO> booksVOS = books.stream().map(e -> new BooksVO(e.getTitle(), e.getAuthor(), e.getInfo())).toList();
        //新建一个BookShelfDTO对象并且赋值
        return new BookShelfDTO(id, booksVOS);
    }

    @Override
    @Transactional
    public boolean booksBorrow(int bookId, int userId) {
        try {
            //查询书籍信息
            Books one = Db.lambdaQuery(Books.class).eq(Books::getId, bookId).one();
            if (one == null) {
                //如果书籍信息不存在直接返回失败
                throw new ServiceException("书籍信息不存在!", null);
            }
            if (one.getNumber() < 1) {
                throw new ServiceException("书籍数量为0无法借阅！", null);
            }
            User user = Db.lambdaQuery(User.class).eq(User::getId, userId).one();
            if (user == null) {
                throw new ServiceException("用户信息不存在！", null);
            }
            //进行借阅操作
            boolean success = this.save(new BookShelf(0, bookId, userId));
            if (success) {
                boolean save = Db.save(new Borrow(0, bookId, userId, BorrowType.OUT, null));
                // 更改书籍数量
                Books upBooks = new Books();
                upBooks.setId(bookId);
                upBooks.setNumber(one.getNumber() - 1);
                Db.lambdaUpdate(Books.class).eq(Books::getId, bookId).update(upBooks);
                return save;
            }
            return false;
        } catch (DataIntegrityViolationException e) {
            //处理可能会产生的唯一索引值异常
            throw new ServiceException("书架中已存在此书籍！", e);
        }
    }

    @Override
    @Transactional
    public boolean booksReturn(int bookId, int userId) {
        //查询书籍信息
        Books one = Db.lambdaQuery(Books.class).eq(Books::getId, bookId).one();
        //查询书架中是否存在此数据
        BookShelf bookShelf=Db.lambdaQuery(BookShelf.class).eq(BookShelf::getBookId,bookId).eq(BookShelf::getUserId,userId).one();
        if (bookShelf==null){
            throw new ServiceException("参数传递有误！未有该书信息！",null);
        }
        //进行归还操作
        boolean success = baseMapper.deleteById(bookShelf.getId())>0;
        if (success) {
            //插入归还记录
            boolean save = Db.save(new Borrow(0, bookId, userId, BorrowType.RETURN, null));
            //修改书籍库存
            Books upBooks = new Books();
            upBooks.setId(bookId);
            upBooks.setNumber(one.getNumber() + 1);
            Db.lambdaUpdate(Books.class).eq(Books::getId, bookId).update(upBooks);
            return save;
        }
        return false;
    }
}
