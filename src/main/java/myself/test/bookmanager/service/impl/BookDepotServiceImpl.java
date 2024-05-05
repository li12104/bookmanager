package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.dao.BookDepotDao;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.BookDepot;
import myself.test.bookmanager.service.BookDepotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDepotServiceImpl extends ServiceImpl<BookDepotDao, BookDepot> implements BookDepotService {
    @Override
    public List<BookDepot> bookGetAll(int current,int size) {
        //构建分页查询
        IPage p=new Page(current,size);
        //进行分页查询操作
        IPage page = getBaseMapper().selectPage(p, null);
        return page.getRecords();//提取数据返回
    }

    @Override
    public List<BookDepot> bookGetByBookName(String bookName,int current,int size) {
        LambdaQueryWrapper<BookDepot> lqw=new LambdaQueryWrapper<>();
        //使用like进行模糊查询
        lqw.like(BookDepot::getBookName,bookName);
        IPage p=new Page(current,size);
        IPage page = getBaseMapper().selectPage(p, lqw);
        List<BookDepot> bookList=page.getRecords();
        return bookList;
    }

    @Override
    public Boolean bookInsert(BookDepot book) {
        try {
            int num=getBaseMapper().insert(book);
            return num>0;
        }catch (Exception e){
            throw new ServiceException("书籍插入失败,请检查书籍是否已存在",e);
        }
    }

    @Override
    public Boolean bookDeleteByName(String bookName) {
        try {
            LambdaUpdateWrapper<BookDepot> lqw=new LambdaUpdateWrapper<>();
            lqw.eq(BookDepot::getBookName,bookName);
            int num=getBaseMapper().delete(lqw);
            return num>0;
        }catch (Exception e){
            throw new ServiceException("此书与其他信息相关联",e);
        }
    }

    @Override
    public Boolean bookUpdate(BookDepot book) {
        try {
            LambdaUpdateWrapper<BookDepot> lqw=new LambdaUpdateWrapper<>();
            lqw.eq(BookDepot::getBookId,book.getBookId());
            int num=getBaseMapper().update(book,lqw);
            return num>0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ServiceException("请确保书名没有重复",e);
        }
    }


}
