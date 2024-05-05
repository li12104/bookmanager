package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.dao.BookDepotDao;
import myself.test.bookmanager.dao.BookMineDao;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.BookDepot;
import myself.test.bookmanager.model.BookMine;
import myself.test.bookmanager.service.BookBorrowService;
import myself.test.bookmanager.service.BookDepotService;
import myself.test.bookmanager.service.BookMineService;
import myself.test.bookmanager.util.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookMineServiceImpl extends ServiceImpl<BookMineDao, BookMine> implements BookMineService {
    @Autowired
    private BookDepotDao bookDepotDao;

    @Autowired
    private BookBorrowService bookBorrowService;
    @Override
    @Transactional
    public Boolean bookBorrow(String bookName) {
        try {
            // 将书籍加入书架
            int num = getBaseMapper().insert(new BookMine(0, UserInfo.getInstance().getUsername(), bookName));
            if (num > 0) {
                // 调用改变借阅状态的服务，并确保它也成功执行
                Boolean borrowSuccess = bookBorrowService.bookChange(UserInfo.getInstance().getUsername(), bookName, 0);
                return borrowSuccess; // 书籍成功加入书架且借阅状态更新成功
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            // 处理ServiceException或其他特定业务异常
            throw new ServiceException("书籍或用户名有误", e);
        }
    }

    @Override
    @Transactional
    public Boolean bookReturn(String bookName) {
        try {
            LambdaUpdateWrapper<BookMine> lqw=new LambdaUpdateWrapper<>();
            lqw.eq(BookMine::getUName,UserInfo.getInstance().getUsername()).eq(BookMine::getBName,bookName);
            int num = getBaseMapper().delete(lqw);
            if (num>0){
                Boolean b = bookBorrowService.bookChange(UserInfo.getInstance().getUsername(), bookName, 1);
                return b;
            }else {
                return false;
            }
        } catch (RuntimeException e) {
            //捕捉可能的异常
            throw new ServiceException("数据异常", e);
        }
    }

    @Override
    public List<BookDepot> bookAllBorrow(int current, int size) {
        //构建查询器
        LambdaQueryWrapper<BookMine> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BookMine::getUName, UserInfo.getInstance().getUsername());
        //将用户书架的信息查询
        List<BookMine> bookMineList = getBaseMapper().selectList(lqw);
        //将书籍信息通过书名列表查询出来
        //将列表转化为书籍名的列表
        List<String> books = bookMineList.stream().map(BookMine::getBName).toList();
        //构建分页查询
        IPage p=new Page(current,size);
        LambdaQueryWrapper<BookDepot> l=new LambdaQueryWrapper<>();
        //构建查询条件
        l.in(BookDepot::getBookName,books);
        //分页查询
        IPage page = bookDepotDao.selectPage(p,l);
        return page.getRecords();//将数据提取出来返回
    }
}
