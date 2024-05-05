package myself.test.bookmanager.controller;

import jakarta.websocket.server.PathParam;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.BookDepot;
import myself.test.bookmanager.service.BookDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookDepotController {

    @Autowired
    private BookDepotService bookService;

    @GetMapping
    public Result BookGetAll(int current, int size){
        List<BookDepot> bookList=bookService.bookGetAll(current,size);
        return new Result(Code.SERVICE_OK,bookList,"请求成功");
    }

    @GetMapping("/{bookName}")
    public Result BookGetByBookName(@PathVariable String bookName,int current,int size){
        List<BookDepot> bookList=bookService.bookGetByBookName(bookName,current,size);
        return new Result(Code.SERVICE_OK,bookList,"请求成功");
    }

    @PostMapping
    public Result BookInsert(@RequestBody BookDepot book){
        try {
            Boolean success=bookService.bookInsert(book);
            return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"新增成功":"新增失败");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }

    @DeleteMapping("/{bookName}")
    public Result BookDeleteByBookName(@PathVariable String bookName){
        try {
            Boolean success=bookService.bookDeleteByName(bookName);
            return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"删除成功":"删除失败");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }
    @PutMapping
    public Result BookUpdate(@RequestBody BookDepot book){
        try {
            Boolean success=bookService.bookUpdate(book);
            return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"更改成功":"更改失败");
        }catch (ServiceException e){
            return new Result(Code.BUSINESS_EXCEPTION,e.getMessage());
        }
    }
}
