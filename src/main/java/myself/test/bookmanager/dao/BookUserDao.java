package myself.test.bookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.model.BookUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookUserDao extends BaseMapper<BookUser> {
}
