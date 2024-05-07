package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("book_comment")
public class BookComment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id=0;
    private String username="";
    private String info;
    private Date time=null;
}
