//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//
//public void createJWT(){
//        try {
//        String jwt = Jwts.builder()
//        .setSubject("users/TzMUocMF4p")
//        .setExpiration(new Date(1300819380))
//        .claim("username", "NSDA")
//        .claim("scope", "self groups/admins")
//        .signWith(
//        SignatureAlgorithm.HS256,
//        "secret".getBytes("UTF-8")
//
//public class WorkingWithJWT {                    )
//                    .compact();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
////    public void validatJWT(String jwtParameter) throws UnsupportedEncodingException {
////        String jwt = jwtParameter;
////        Jws<Claims> claims = Jwts.parser()
////                .setSigningKey("secret".getBytes("UTF-8"))
////                .parseClaimsJws(jwt);
////        String scope = (String) claims.getBody().get("scope");
////        assertEquals(scope, "self groups/admins");
////
////    }
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        WorkingWithJWT j1=new WorkingWithJWT();
//        j1.createJWT();
//    }
//}
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider; //TODO: clean up via https://github.com/jwtk/jjwt/issues/350
import java.security.Key;

class WorkingWithJWT{
   public static String  createJwt(){
        Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return compactJws;
    }

    public static void main(String[] args) {
        String ans=createJwt();
        System.out.println(ans);
    }
}