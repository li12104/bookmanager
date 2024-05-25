package myself.test.bookmanager;

import myself.test.bookmanager.service.IBookShelfService;
import myself.test.bookmanager.service.IBorrowService;
import myself.test.bookmanager.service.ICommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookmanagerApplicationTests {

    @Autowired
    private ICommentService commentService;

    @Test
    void contextLoads() {
    }

}
