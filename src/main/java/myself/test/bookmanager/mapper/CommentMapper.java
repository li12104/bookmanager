package myself.test.bookmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT c.`message`,c.`create_time`,u.`username` FROM `comment` c" +
            " LEFT JOIN `user` u ON c.`user_id` = u.`id` ")
    List<CommentDTO> getAllComment();
}
