package myself.test.bookmanager.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {

    private String username;
    private String pwd;
}
