package myself.test.bookmanager.controller;

import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.BookDepot;
import myself.test.bookmanager.service.BookBorrowService;
import myself.test.bookmanager.service.BookMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@RestController
@RequestMapping("/api/mine")
public class BookMineController {
    @Autowired
    private BookMineService bookMineService;

    @GetMapping
    public Result bookMineAllBook(int current,int size){
        List<BookDepot> books = bookMineService.bookAllBorrow(current, size);
        return new Result(Code.SERVICE_OK,books,"查询成功");
    }
    @PostMapping
    public Result bookBorrow(String bookName){
        try {
            Boolean success = bookMineService.bookBorrow(bookName);
            return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"加入成功":"加入失败");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }

    @DeleteMapping
    public Result bookReturn(String bookName){
        try {
            Boolean success = bookMineService.bookReturn(bookName);
            return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"移除成功":"移除失败");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }
}
