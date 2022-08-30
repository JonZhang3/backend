package com.future.common.utils.jwt;

import java.time.Duration;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JWT 工具
 */
public final class TokenUtils {

    private TokenUtils() {
    }

    private static final String ISSUER = "future";
    private static final String REQUEST_HEADER_TOKEN = "X-Access-Token";
    private static final String DEFAULT_SECRET = "$$future$$";

    /**
     * 从请求头中获取 token
     * 
     * @param request 请求
     * @return JWT
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(REQUEST_HEADER_TOKEN);
    }

    @NotNull
    public static String generate(@NotNull UserPayload payload, @NotNull Duration validTime) {
        return generate(payload, validTime, DEFAULT_SECRET);
    }

    /**
     * 生成 token
     *
     * @param payload   负载内容
     * @param validTime 有效时间
     * @param secret    密钥，通常使用用户密码
     * @return JWT
     */
    @NotNull
    public static String generate(@NotNull UserPayload payload, @NotNull Duration validTime, String secret) {
        if (StringUtils.isEmpty(secret)) {
            secret = DEFAULT_SECRET;
        }
        Date expiresAt = new Date(System.currentTimeMillis() + validTime.getSeconds() * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(expiresAt)
                .withPayload(payload.map())
                .sign(algorithm);
    }

    public static boolean validate(@NotNull String token) {
        return validate(token, DEFAULT_SECRET);
    }

    /**
     * 校验 Token
     *
     * @param token  JWT
     * @param secret 密钥，通常使用用户密码
     * @return 校验通过返回 true
     */
    public static boolean validate(@NotNull String token, String secret) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    @Nullable
    public static UserPayload validateAndGetPayload(@NotNull String token) {
        return validateAndGetPayload(token, DEFAULT_SECRET);
    }

    /**
     * 校验并获取 JWT 中的负载内容
     *
     * @param token  JWT
     * @param secret 密钥，通常使用用户密码
     * @return JWT 中的负载
     */
    @Nullable
    public static UserPayload validateAndGetPayload(String token, @NotNull String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            return new UserPayload(jwt.getClaims());
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * 仅仅获取 JWT 中的负载内容，不对 JWT 进行校验
     *
     * @param token JWT
     * @return JWT 中的负载内容
     */
    @Nullable
    public static UserPayload parse(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new UserPayload(jwt.getClaims());
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
