package myself.test.bookmanager.controller;

import myself.test.bookmanager.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping
    public Result getAllComment(){
        return new Result(Code.SERVICE_OK,commentService.getAllComment(),"请求成功");
    }
}
