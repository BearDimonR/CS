package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.jsonwebtoken.Jwts;
import ua.com.ke4a_store.network.crypt.MD5;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;


//      /login
public class LoginHandler implements HttpHandler {

    private Connection connection;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers headers = exchange.getRequestHeaders();

        String login = headers.getFirst("login");
        String password = headers.getFirst("password");
        OutputStream os = exchange.getResponseBody();
        if (login == null || password == null) {
            exchange.sendResponseHeaders(401, 0);
            os.close();
            return;
        }
        String jws = Jwts.builder().setSubject(login).signWith(Auth.KEY).compact();

        if (checkUser(login, password)) {
            exchange.sendResponseHeaders(200, jws.getBytes().length);
            os.write(jws.getBytes());
        } else {

        }
        os.close();
    }

    private boolean checkUser(String login, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, MD5.getMd5(password));
            ResultSet res = preparedStatement.executeQuery();
            return res.next();
        } catch (SQLException e) {
            return false;
        }
    }
}