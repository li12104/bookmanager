package myself.test.bookmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myself.test.bookmanager.enums.BorrowType;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto implements Serializable {
    private String user;
    private String book;
    private BorrowType type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")//不将日期转为时间戳格式
    private Date time;
}
