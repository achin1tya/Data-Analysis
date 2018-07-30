import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class WorkingWithJWT {
    public void createJWT(){
        try {
            String jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(new Date(1300819380))
                    .claim("username", "NSDA")
                    .claim("scope", "self groups/admins")
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
//    public void validatJWT(String jwtParameter) throws UnsupportedEncodingException {
//        String jwt = jwtParameter;
//        Jws<Claims> claims = Jwts.parser()
//                .setSigningKey("secret".getBytes("UTF-8"))
//                .parseClaimsJws(jwt);
//        String scope = (String) claims.getBody().get("scope");
//        assertEquals(scope, "self groups/admins");
//
//    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        WorkingWithJWT j1=new WorkingWithJWT();
        j1.createJWT();
    }
}
