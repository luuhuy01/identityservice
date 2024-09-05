package com.ecommerce.identityservice.utils;

import com.ecommerce.identityservice.dto.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@UtilityClass
@Slf4j
public class JwtUtil {

    public static String generateToken(UserResponse userResponse, String signKey, long expiresIn) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        ObjectMapper objectMapper = new ObjectMapper();
        // handle type LocalDate if it needs
        objectMapper.registerModule(new JavaTimeModule());
        try {
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(userResponse.getUsername())
                    .issuer("com.ecommerce.identityservice")
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(expiresIn, ChronoUnit.MILLIS).toEpochMilli()
                    ))
                    .claim("customClaim", "custom")
                    .claim("scope", userResponse.getRoles())
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);
            jwsObject.sign(new MACSigner(signKey.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e) {
            log.error("Can not get token: ", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyToken(String token, String signKey) {
        try {
            JWSVerifier verifier = new MACVerifier(signKey.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
            boolean verified = signedJWT.verify(verifier);

            return verified && expirationDate.after(new Date());
        } catch (Exception e) {
            log.error("Can not verify token: ", e);
            throw new RuntimeException(e);
        }
    }
}
