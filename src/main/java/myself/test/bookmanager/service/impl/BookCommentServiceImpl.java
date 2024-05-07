package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.dao.BookCommentDao;
import myself.test.bookmanager.model.BookComment;
import myself.test.bookmanager.service.BookCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCommentServiceImpl extends ServiceImpl<BookCommentDao,BookComment> implements BookCommentService {
    @Override
    public List<BookComment> getAllComment(int current, int size) {
        IPage p=new Page(current,size);
        LambdaQueryWrapper<BookComment> lqw=new LambdaQueryWrapper<>();
        IPage page = baseMapper.selectPage(p, lqw);
        return page.getRecords();
    }

    @Override
    public Boolean addComment(BookComment comment) {
        int num = baseMapper.insert(comment);
        return num>0;
    }

    @Override
    public Boolean deleteComment(int id) {
        int num = baseMapper.deleteById(id);
        return num>0;
    }
}
