package myself.test.bookmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import myself.test.bookmanager.enums.BorrowType;
import myself.test.bookmanager.model.Borrow;
import myself.test.bookmanager.model.dto.BorrowDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {

    /**
     * 查询所有借阅记录
     *
     * @return
     */
    @Select("SELECT  u.username AS `user`,b.title as book ,br.type,br.time FROM borrow br " +
            "JOIN `user` u ON br.user_id = u.id JOIN books b ON br.book_id = b.id ")
    List<BorrowDto> getBorrowLog();

    /**
     * 根据类型查询所有借阅记录
     *
     * @return
     */
    @Select("SELECT  u.username AS `user`,b.title as book ,br.type,br.time FROM borrow br " +
            "JOIN `user` u ON br.user_id = u.id JOIN books b ON br.book_id = b.id where br.type= #{type}")
    List<BorrowDto> getBorrowLogByType(BorrowType type);

    @Select("SELECT  u.username AS `user`,b.title as book ,br.type,br.time FROM borrow br " +
            "JOIN `user` u ON br.user_id = u.id JOIN books b ON br.book_id = b.id where u.id = #{userId}")
    List<BorrowDto> getUserBorrowLog(int userId);

    @Select("SELECT  u.username AS `user`,b.title as book ,br.type,br.time FROM borrow br " +
            "JOIN `user` u ON br.user_id = u.id JOIN books b ON br.book_id = b.id" +
            " where u.id = #{userId} and br.type = #{type}")
    List<BorrowDto> getUserBorrowLogByType(int userId,BorrowType type);
}
