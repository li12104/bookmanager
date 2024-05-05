package myself.test.bookmanager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("book_user")
@AllArgsConstructor
@NoArgsConstructor
public class BookUser {

    @TableId(type = IdType.AUTO,value = "u_id")
    private Integer uId=0;
    private String username;
    private String password;

    @TableField(value = "is_admin")
    private int isAdmin=1;
    private String nickname;

    // ... 其他字段的getter和setter方法 ...

    public void setUsername(String username) {
        this.username = username;
        this.nickname = calculateNickname(username);
    }

    public String getNickname() {
        return nickname;
    }

    //设置默认的昵称
    private String calculateNickname(String username) {
        return (username != null ? username : "") + "12138";
    }
}
