package myself.test.bookmanager.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import myself.test.bookmanager.exception.ServiceException;

@Getter
public enum CommentOrder {
    ASC(1,"ASC"),
    DESC(2,"DESC");
    @EnumValue
    private final int type;
    private final String  value;

    CommentOrder(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public static CommentOrder of(int type){
        for (CommentOrder type1 : CommentOrder.values()){
            if (type1.getType()==type){
                return type1;
            }
        }
        throw new ServiceException("没有此类型！",null);
    }
}
