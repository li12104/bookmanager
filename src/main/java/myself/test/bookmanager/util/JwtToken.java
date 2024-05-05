package myself.test.bookmanager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    private static final String SECRET_KEY = "qazwsxedcrfv"; // 请使用你自己的密钥

    // 生成JWT token
    public static String generateToken(String username,int isAdmin) {
        // 设置token的过期时间
        String token="";
        long ttlMillis = 1000L * 60 * 60 * 24; // 1天
        Date expirationDate = new Date(System.currentTimeMillis() + ttlMillis);
        try {
            // 创建一个算法实例，这里使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            // 设置头部信息
            Boolean admin=isAdmin==0?true : false;
            // 生成JWT
            token = JWT.create()
                    .withClaim("isAdmin",admin)
                    .withClaim("username",username)
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
            return isAfterNow==false;
        } catch (JWTVerificationException e){
            return false;
        }
    }

    public static String getUsername(String token){
        try {
            return JWT.decode(token).getClaim("username").asString();//获取用户名
        } catch (JWTVerificationException e){
            return null;
        }
    }
}
