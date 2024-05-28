package myself.test.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
