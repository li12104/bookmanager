package myself.test.bookmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import myself.test.bookmanager.enums.CommentOrder;
import myself.test.bookmanager.model.Comment;
import myself.test.bookmanager.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT c.id,c.`message`,c.`create_time`,u.`username` FROM `comment` c" +
            " LEFT JOIN `user` u ON c.`user_id` = u.`id` order by c.id ASC ")
    List<CommentDTO> getAllCommentAsc();

    @Select("SELECT c.id,c.`message`,c.`create_time`,u.`username` FROM `comment` c" +
            " LEFT JOIN `user` u ON c.`user_id` = u.`id` order by c.id DESC ")
    List<CommentDTO> getAllCommentDesc();

}
