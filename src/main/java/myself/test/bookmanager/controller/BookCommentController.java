package myself.test.bookmanager.controller;

import myself.test.bookmanager.model.BookComment;
import myself.test.bookmanager.service.BookCommentService;
import myself.test.bookmanager.util.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class BookCommentController {

    @Autowired
    private BookCommentService bookCommentService;
    @GetMapping
    public Result getAllComment(int current, int size){
        List<BookComment> comments=bookCommentService.getAllComment(current,size);
        return new Result(Code.SERVICE_OK,comments,"请求成功");
    }
    @PostMapping
    public Result addComment(@RequestBody BookComment bookComment){
        bookComment.setUsername(UserInfo.getInstance().getUsername());
        boolean success=bookCommentService.addComment(bookComment);
        return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"新增成功":"新增失败");
    }

    @DeleteMapping("{id}")
    public Result deleteComment(@PathVariable int id){
        boolean success=bookCommentService.deleteComment(id);
        return new Result(success?Code.SERVICE_OK:Code.SERVICE_ERROR,success?"删除成功":"删除失败");
    }
}
