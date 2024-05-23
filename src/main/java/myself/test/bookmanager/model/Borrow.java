package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myself.test.bookmanager.enums.BorrowType;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "book_id")
    private Integer bookId;
    @TableField(value = "user_id")
    private Integer userId;
    private BorrowType type;
    private Date time=null;
}
