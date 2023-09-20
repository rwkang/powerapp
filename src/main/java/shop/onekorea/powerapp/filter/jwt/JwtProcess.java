package shop.onekorea.powerapp.filter.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JwtProcess {
    public static String create(int userId) {
        String jwtToken = JWT.create().withSubject(JwtProps.SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRESAT))
                .withClaim("id", userId)
                .sign(Algorithm.HMAC512(JwtProps.SECRET));
        return jwtToken;
    }

    public static String verify(String jwtToken) {
//    public static int verify(String jwtToken) {
        DecodedJWT decodeJwt = JWT.require(Algorithm.HMAC512(JwtProps.SECRET)).build().verify(jwtToken);

        String userId = decodeJwt.getClaim("userid").asString();
//        Integer userId = decodeJwt.getClaim("id").asInt();


        System.out.println("JwtProcess.java.27.UserId: " + userId);

        return userId;

    }
}
