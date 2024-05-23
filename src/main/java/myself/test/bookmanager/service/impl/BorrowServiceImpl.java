package myself.test.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import myself.test.bookmanager.enums.BorrowType;
import myself.test.bookmanager.mapper.BorrowMapper;
import myself.test.bookmanager.model.Borrow;
import myself.test.bookmanager.model.dto.BorrowDto;
import myself.test.bookmanager.service.IBorrowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {

    @Override
    public List<BorrowDto> getBorrowLog() {
        return baseMapper.getBorrowLog();
    }

    @Override
    public List<BorrowDto> getBorrowUserLog(int userId) {
        System.out.println(userId);
        return baseMapper.getUserBorrowLog(userId);
    }

    @Override
    public List<BorrowDto> getBorrowLog(BorrowType type) {
        return baseMapper.getBorrowLogByType(type);
    }

    @Override
    public List<BorrowDto> getBorrowUserLog(int userId, BorrowType type) {
        return baseMapper.getUserBorrowLogByType(userId,type);
    }
}
