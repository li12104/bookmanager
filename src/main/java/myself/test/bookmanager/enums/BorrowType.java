package myself.test.bookmanager.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import myself.test.bookmanager.exception.ServiceException;

@Getter
public enum BorrowType {
    OUT(0,"借出"),
    RETURN(1,"归还");
    @EnumValue
    private final  int type;
    @JsonValue
    private final  String  dsc;

    BorrowType(int type, String dsc) {
        this.type = type;
        this.dsc = dsc;
    }

    public static BorrowType of(int type){
        for (BorrowType type1 : BorrowType.values()){
            if (type1.getType()==type){
                return type1;
            }
        }
        throw new ServiceException("没有此类型！",null);
    }
}
