package myself.test.bookmanager.controller;

public class Code {

    // 业务成功的状态码
    public static final int SERVICE_OK = 200;

    // 业务逻辑错误的状态码（从1000开始，避免与HTTP状态码冲突）
    public static final int SERVICE_ERROR = 1001;

    // 业务逻辑中抛出的异常状态码
    public static final int BUSINESS_EXCEPTION = 1002;

    // 系统级错误的状态码
    public static final int SYSTEM_ERROR = 1003;

    //方法路径错误的异常状态码
    public static final int METHOD_NOTFOUND = 1004;

    //请求中未包含token的状态码
    public static final int TOKEN_NOTFOUND = 1005;

    //token过期的状态码
    public static final int TOKEN_ISLATE = 1006;

    //资源路径错误的异常状态码
    public static final int RESOURCE_NOTFOUND = 1007;

    public static final int NOT_QX=1008;

}
