package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    /**
     * 获取所有留言信息
     * @return
     */
    List<CommentDTO> getAllComment();
}
