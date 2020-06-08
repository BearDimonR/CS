package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class Auth extends Authenticator{

    public static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Override
    public Authenticator.Result authenticate(HttpExchange httpExchange) {
        try{
            String token = httpExchange.getRequestHeaders().getFirst("token");
            if (token == null)
                throw new JwtException("Wrong token");
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        }
        catch (JwtException ex){
            return new Authenticator.Failure(403);
        }
        return new Authenticator.Success(new HttpPrincipal("c0nst", "realm"));
    }
}
