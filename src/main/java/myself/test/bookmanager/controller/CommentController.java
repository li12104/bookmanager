package myself.test.bookmanager.controller;

import myself.test.bookmanager.enums.CommentOrder;
import myself.test.bookmanager.exception.ServiceException;
import myself.test.bookmanager.model.vo.CommentVO;
import myself.test.bookmanager.service.ICommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping()
    public Result getAllComment() {
        return new Result(Code.SERVICE_OK, commentService.getAllComment(CommentOrder.ASC), "请求成功!");
    }

    @GetMapping("/{type}")
    public Result getAllComment(@PathVariable int type) {
        try {
            return new Result(Code.SERVICE_OK, commentService.getAllComment(CommentOrder.of(type)), "请求成功!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @PostMapping
    public Result newComment(@RequestBody CommentVO commentVO){
        try {
            boolean success=commentService.newComment(commentVO);
            if (success){
                return new Result(Code.SERVICE_OK,"新增成功!");
            }
            return new Result(Code.SERVICE_ERROR,"新增失败!");
        } catch (ServiceException e) {
            return new Result(Code.BUSINESS_EXCEPTION, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteCommentById(@PathVariable int id){
        boolean success=commentService.deleteCommentById(id);
        if (success){
            return new Result(Code.SERVICE_OK,"删除成功!");
        }
        return new Result(Code.SERVICE_ERROR,"删除失败!");
    }
}
