package myself.test.bookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.model.BookMine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMineDao extends BaseMapper<BookMine> {

}
