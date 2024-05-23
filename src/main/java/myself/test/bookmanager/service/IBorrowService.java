package myself.test.bookmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import myself.test.bookmanager.enums.BorrowType;
import myself.test.bookmanager.model.Borrow;
import myself.test.bookmanager.model.dto.BorrowDto;

import java.util.List;

public interface IBorrowService extends IService<Borrow> {

    /**
     * 获取所有用户借阅记录
     */
    List<BorrowDto> getBorrowLog();

    /**
     * 获取用户借阅记录
     */
    List<BorrowDto> getBorrowUserLog(int userId);

    /**
     *根据类型查看记录
     */
    List<BorrowDto> getBorrowLog(BorrowType type);

    /**
     * 根据类型获取用户借阅记录
     */
    List<BorrowDto> getBorrowUserLog(int userId, BorrowType type);
}
