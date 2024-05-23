package myself.test.bookmanager.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserAdmin {
    ADMIN(0,"管理员"),
    USER(1,"用户");
    @EnumValue
    private final int num;
    @JsonValue//表示用level值返回数据
    private final String level;

    UserAdmin(int num, String level) {
        this.num = num;
        this.level = level;
    }
}
