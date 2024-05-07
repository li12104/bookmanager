package myself.test.bookmanager.controller;

import myself.test.bookmanager.model.BookBorrow;
import myself.test.bookmanager.service.BookBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/record")
public class BookBorrowController {

    @Autowired
    private BookBorrowService bookBorrowService;

    @GetMapping
    public Result getAllBookBorrowLog(int current,int size){
        List<BookBorrow> bookLogs = bookBorrowService.bookLog(current, size);
        return new Result(Code.SERVICE_OK,bookLogs,"请求成功");
    }
}
