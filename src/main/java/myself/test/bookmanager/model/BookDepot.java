package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("book_depot")
@AllArgsConstructor
@NoArgsConstructor
public class BookDepot {

    @TableId(type = IdType.AUTO,value = "book_id")
    private Integer bookId=0;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "book_author")
    private String bookAuthor;

    @TableField(value = "book_info")
    private String bookInfo;
}
