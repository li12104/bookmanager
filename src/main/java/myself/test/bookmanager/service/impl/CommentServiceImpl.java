package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.mapper.CommentMapper;
import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;
import myself.test.bookmanager.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public List<CommentDTO> getAllComment() {
        return baseMapper.getAllComment();
    }
}
