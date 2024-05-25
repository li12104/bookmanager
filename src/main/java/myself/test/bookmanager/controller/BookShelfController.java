package myself.test.bookmanager.controller;

import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.service.IBookShelfService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookshelf")
public class BookShelfController {

    @Autowired
    private IBookShelfService bookShelfService;

    @GetMapping("/{id}")
    public Result getUserBooks(@PathVariable int id) {
        try {
            return new Result(Code.SERVICE_OK, bookShelfService.getUserBooks(id), "请求成功!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @PostMapping("/out")
    public Result booksBorrow(@Param("bookId") int bookId, @Param("userId") int userId) {
        try {
            boolean success = bookShelfService.booksBorrow(bookId, userId);
            if (success) {
                return new Result(Code.SERVICE_OK, "加入成功!");
            }
            return new Result(Code.SERVICE_ERROR, "加入失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @DeleteMapping
    public Result booksReturn(@Param("bookId") int bookId, @Param("userId") int userId) {
        try {
            boolean success = bookShelfService.booksReturn(bookId, userId);
            if (success) {
                return new Result(Code.SERVICE_OK, "归还成功!");
            }
            return new Result(Code.SERVICE_ERROR, "归还失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }

    }
}
