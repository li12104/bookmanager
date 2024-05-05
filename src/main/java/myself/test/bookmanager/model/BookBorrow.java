package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@TableName("book_borrow")
@Data
public class BookBorrow {

    @TableId(value = "date_id", type = IdType.AUTO)
    private Integer dataId;

    @TableField(value = "u_name")
    private String uName;
    @TableField(value = "borrow_data")
    private Date borrowDate;

    @TableField(value = "borrow_book")
    private String borrowBook;

    private int type;

    public BookBorrow(Integer dataId, String uName, String borrowBook, int type) {
        this.dataId = dataId;
        this.uName = uName;
        this.borrowBook = borrowBook;
        this.type = type;
    }
}
