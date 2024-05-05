package myself.test.bookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.model.BookDepot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDepotDao extends BaseMapper<BookDepot> {
}
