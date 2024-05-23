package myself.test.bookmanager.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class MineMd5 {

    public static String pwdMd(String pwd){
        String md5= DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        return md5;
    }
}
