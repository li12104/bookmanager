package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("book_mine")
public class BookMine {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "u_name")
    private String uName;

    @TableField(value = "b_name")
    private String bName;
}
