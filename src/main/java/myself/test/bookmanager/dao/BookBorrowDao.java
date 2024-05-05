package myself.test.bookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.model.BookBorrow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookBorrowDao extends BaseMapper<BookBorrow> {
}
