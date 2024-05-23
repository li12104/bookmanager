package myself.test.bookmanager.model.user;

import lombok.Data;

@Data
public class Register {

    private String username;
    private String pwd;
    private String nickname;

    public Register(String username, String pwd, String nickname) {
        this.username = username;
        this.pwd = pwd;
        this.nickname = nickname == null ? calculateNickname(username) : nickname;
    }

    private String calculateNickname(String username) {
        return (username != null ? username : "") + "12138";
    }
}
