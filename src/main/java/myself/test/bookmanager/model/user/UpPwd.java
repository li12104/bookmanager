package myself.test.bookmanager.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpPwd {
    private String username;
    private String pwd;
    private String newPwd;
}
