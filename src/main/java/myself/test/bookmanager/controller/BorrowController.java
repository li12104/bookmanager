package myself.test.bookmanager.controller;

import myself.test.bookmanager.enums.BorrowType;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.service.IBorrowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @GetMapping
    private Result getAllBorrowLog() {
        return new Result(Code.SERVICE_OK, borrowService.getBorrowLog(), "请求成功");
    }

    @GetMapping("/{type}")
    private Result getBorrowLog(@PathVariable int type) {
        try {
            return new Result(Code.SERVICE_OK,borrowService.getBorrowLog(BorrowType.of(type)), "请求成功");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @GetMapping("/user")
    public Result getUserBorrowLog(int userId) {
        return new Result(Code.SERVICE_OK, borrowService.getBorrowUserLog(userId), "请求成功");
    }

    @GetMapping("/user/type")
    public Result getUserBorrowLog(int userId, int type) {
        try {
            return new Result(Code.SERVICE_OK, borrowService.getBorrowUserLog(userId, BorrowType.of(type)), "请求成功");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }
}
