package myself.test.bookmanager.util;

public class UserInfo {

    // 私有静态实例，在类加载时初始化
    private static final UserInfo INSTANCE = new UserInfo();

    // 私有构造函数，防止外部实例化
    private UserInfo() {}

    // 用户名字段
    private String username;

    private Boolean isAdmin;

    // 公共的静态方法，返回该类的唯一实例
    public static UserInfo getInstance() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
