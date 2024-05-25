package myself.test.bookmanager.controller;

import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.Books;
import myself.test.bookmanager.model.vo.BooksVO;
import myself.test.bookmanager.service.IBookShelfService;
import myself.test.bookmanager.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private IBooksService IBooksService;

    @GetMapping()
    public Result getAllBooks(Integer num){
        return new Result(Code.SERVICE_OK, IBooksService.getAllBooks(num),"请求成功!");
    }
    @GetMapping("/byName")
    public Result getBooks(String name,Integer num){
        return new Result(Code.SERVICE_OK, IBooksService.getBooks(name,num),"请求成功!");
    }

    @PostMapping
    public Result saveBooks(@RequestBody Books books){
        try {
            boolean success = IBooksService.saveBooks(books);
            if (success) {
                return new Result(Code.SERVICE_OK, "新增成功!");
            }
            return new Result(Code.SERVICE_ERROR, "新增失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @PutMapping
    public Result updateBooks(@RequestBody Books books){
        try {
            boolean success = IBooksService.updateBooks(books);
            if (success) {
                return new Result(Code.SERVICE_OK, "修改成功!");
            }
            return new Result(Code.SERVICE_ERROR, "修改失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable int id){
        boolean success = IBooksService.deleteBooksById(id);
        if (success) {
            return new Result(Code.SERVICE_OK, "删除成功!");
        }
        return new Result(Code.SERVICE_ERROR, "删除失败!");
    }

    @GetMapping("/{id}")
    public Result getBook(@PathVariable int id){
        BooksVO book = IBooksService.getBook(id);
        if (book==null){
            return new Result(Code.SERVICE_ERROR, "未查询到该书籍!");
        }
        return new Result(Code.SERVICE_OK, book,"请求成功!");
    }
}
