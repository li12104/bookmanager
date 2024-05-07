package myself.test.bookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.model.BookComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookCommentDao extends BaseMapper<BookComment> {
}
