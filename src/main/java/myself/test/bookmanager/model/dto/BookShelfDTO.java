package myself.test.bookmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myself.test.bookmanager.model.BookShelf;
import myself.test.bookmanager.model.Books;
import myself.test.bookmanager.model.vo.BooksVO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookShelfDTO {

    private Integer userId;
    private List<BooksVO> booksList;
}
