package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import myself.test.bookmanager.enums.CommentOrder;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.mapper.CommentMapper;
import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;
import myself.test.bookmanager.model.user.User;
import myself.test.bookmanager.model.vo.CommentVO;
import myself.test.bookmanager.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public List<CommentDTO> getAllComment(CommentOrder order) {
        if (order==CommentOrder.ASC){
            return baseMapper.getAllCommentAsc();
        }
        return baseMapper.getAllCommentDesc();
    }

    @Override
    public boolean deleteCommentById(int id) {
        return removeById(id);
    }

    @Override
    public boolean newComment(CommentVO commentVO) {
        //判断用户id是否存在
        User user = Db.lambdaQuery(User.class).eq(User::getId, commentVO.getUserId()).one();
        if (user==null){
            throw new ServiceException("用户信息不存在!",null);
        }
        Comment comment= new Comment();
        BeanUtils.copyProperties(commentVO,comment);
        return save(comment);
    }
}
