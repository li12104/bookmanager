package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.enums.CommentOrder;
import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;
import myself.test.bookmanager.model.vo.CommentVO;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    /**
     * 获取所有留言信息
     * @return
     */
    List<CommentDTO> getAllComment(CommentOrder order);

    boolean deleteCommentById(int id);

    boolean newComment(CommentVO commentVO);
}
