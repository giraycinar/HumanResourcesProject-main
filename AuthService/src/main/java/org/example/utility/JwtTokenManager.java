package org.example.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.utility.enums.ERole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    @Value("${jwt.sKey}: s12345 ")
    private String sKey="sKey";

    private String issuer="issuer";

    private String audience="audience";

    public Optional<String> createToken(Long id, ERole role){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .sign(Algorithm.HMAC512(sKey));
            return Optional.of(token);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


   public Optional<Long> validateToken(String token){
       try{
           Algorithm algorithm = Algorithm.HMAC512(sKey);
           JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
           DecodedJWT decodedJWT = verifier.verify(token);
           if(decodedJWT == null)
               return Optional.empty();
           Long authId = decodedJWT.getClaim("authid").asLong();
           return Optional.of(authId);
       }catch (Exception e){
           return Optional.empty();
       }
   }

}


