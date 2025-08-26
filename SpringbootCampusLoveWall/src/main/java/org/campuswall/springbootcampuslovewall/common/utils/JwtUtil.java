package org.campuswall.springbootcampuslovewall.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.campuswall.springbootcampuslovewall.common.config.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * JWT工具类，用于生成、验证和解析JWT令牌
 */
@Component
public class JwtUtil {

    private static String secret;
    private static long accessTokenExpire;
    private static long refreshTokenExpire;

    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 初始化JWT配置参数
     * 从JwtProperties中获取密钥和过期时间配置，并初始化算法和验证器
     */
    @PostConstruct
    public void init() {
        secret = jwtProperties.getSecret();
        accessTokenExpire = jwtProperties.getAccessTokenExpire();
        refreshTokenExpire = jwtProperties.getRefreshTokenExpire();

        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }

    /**
     * 生成访问令牌
     * @param userId 用户ID
     * @param roles 用户角色列表
     * @return 生成的JWT访问令牌
     */
    public static String generateAccessToken(String userId, List<String> roles) {
        return JWT.create()
                .withSubject(userId)
                .withClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpire))
                .sign(algorithm);
    }

    /**
     * 生成刷新令牌
     * @param userId 用户ID
     * @return 生成的JWT刷新令牌
     */
    public static String generateRefreshToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpire))
                .sign(algorithm);
    }

    /**
     * 验证JWT令牌
     * @param token 待验证的JWT令牌
     * @return 解码后的JWT对象
     */
    public static DecodedJWT verifyToken(String token) {
        return verifier.verify(token);
    }

    /**
     * 从HTTP请求中提取JWT令牌
     * @param request HTTP请求对象
     * @return 提取到的JWT令牌，如果没有则返回null
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    /**
     * 获取令牌剩余有效时间
     * @param token JWT令牌
     * @return 剩余有效时间（毫秒），如果令牌无效则返回0
     */
    public static long getRemainingTime(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getExpiresAt().getTime() - System.currentTimeMillis();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 验证令牌是否有效
     * @param token 待验证的JWT令牌
     * @return 令牌有效返回true，否则返回false
     */
    public static boolean isTokenValid(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

