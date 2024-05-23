package myself.test.bookmanager.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myself.test.bookmanager.enums.UserAdmin;
import myself.test.bookmanager.model.user.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;

    private UserAdmin isAdmin;
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")//不将日期转为时间戳格式
    private Date createTime = null;

    public static UserDTO of(User user){
        return new UserDTO(user.getId(),user.getUsername(),user.getIsAdmin(),user.getNickname(),user.getCreateTime());
    }
}
