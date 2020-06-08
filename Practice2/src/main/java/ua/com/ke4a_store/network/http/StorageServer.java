package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StorageServer {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");

            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8089), 0);
            HttpContext goodContext = server.createContext("/goods", new StorageHandler());
            goodContext.setAuthenticator(new Auth());

            server.createContext("/groups", new GroupsHandler());
            server.createContext("/edit", new EditHandler());
            server.createContext("/login", new LoginHandler());

            server.setExecutor(executor);
        //?    server.setExecutor(null);
            server.start();
            System.out.println("Storage server has been started");

            try {
            executor.submit((Runnable) server::getExecutor);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }


    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}