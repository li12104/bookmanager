package myself.test.bookmanager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

public class JwtToken {

    private static final String SECRET_KEY = "lijianbookmanger";

    // 生成JWT token
    public static String generateToken(int type) {
        // 设置token的过期时间
        String token="";
        long ttlMillis = 1000L * 60 * 60 * 24; // 1天
        Date expirationDate = new Date(System.currentTimeMillis() + ttlMillis);
        try {
            // 创建一个算法实例，这里使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            // 生成JWT
            boolean admin=type==0?true:false;
            token = JWT.create()
                    .withClaim("admin",admin)
                    .withClaim("exp", expirationDate)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            return null;
        }
        return token;
    }

    /**
     * 检验token是否正确
     * @param token 需要校验的token
     * @return 校验是否成功
     */
    public static boolean verify(String token){
        try {
            Date exp=JWT.decode(token).getClaim("exp").asDate();//获取过期时间
            Boolean isAfterNow=new Date().after(exp);//比对日期
            return isAfterNow;
        } catch (JWTVerificationException e){
            return false;
        }
    }


    public static boolean isAdmin(String token){
        try {
            return JWT.decode(token).getClaim("admin").asBoolean();//获取用户名
        } catch (JWTVerificationException e){
            e.printStackTrace();
            return false;
        }
    }
}
