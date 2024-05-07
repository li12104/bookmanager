package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.BookComment;

import java.util.List;

public interface BookCommentService extends IService<BookComment> {
    /**
     * 获取所有留言信息，分页查询
     * @param current
     * @param size
     * @return
     */
    List<BookComment> getAllComment(int current,int size);

    /**
     * 新增留言
     * @param comment
     * @return
     */
    Boolean addComment(BookComment comment);

    /**
     * 删除留言
     * @param id
     * @return
     */
    Boolean deleteComment(int id);
}
