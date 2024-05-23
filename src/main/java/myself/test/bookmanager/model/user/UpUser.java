package myself.test.bookmanager.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpUser {
    private int id;
    private String pwd;
    private String newPwd;
    private String nickname;
}
