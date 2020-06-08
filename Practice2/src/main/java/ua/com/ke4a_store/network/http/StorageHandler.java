package ua.com.ke4a_store.network.http;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.network.crypt.Encriptor;
import ua.com.ke4a_store.network.http.commands.impl.GoodsHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageHandler implements HttpHandler {
    private Connector connector;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        switch (method){
            case "GET":
                getGood(exchange);
                break;
            case "DELETE":
                deleteGood(exchange);
                break;
            case "PUT":
                createGood(exchange);
                break;
            case "POST":
                changeGood(exchange);
        }
    }

    private void getGood(HttpExchange exchange) throws IOException{
        String path = exchange.getRequestURI().getPath();
        GoodsHandler goodById = new GoodsHandler(Integer.parseInt(path.substring(path.lastIndexOf("/")+1)));
        OutputStream os = exchange.getResponseBody();
        try {
           goodById.callCommand();
        } catch (SQLException e) {
            exchange.sendResponseHeaders(404, 0);
        }
        finally {
            os.close();
        }
    }

    private void deleteGood(HttpExchange exchange) throws IOException{
        String path = exchange.getRequestURI().getPath();
        OutputStream os = exchange.getResponseBody();
        try {
            GoodsHandler goodsHandler = new GoodsHandler(Integer.parseInt(path.substring(path.lastIndexOf("/")+1)));
            goodsHandler.callCommand("delete");
            exchange.sendResponseHeaders(204, -1);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createGood(HttpExchange exchange) throws IOException
    {
        InputStream is = exchange.getRequestBody();

        OutputStream os = exchange.getResponseBody();
        try {
            ArrayList<String> params = getParams(is);
            if(Double.parseDouble(params.get(6))< 0)
                throw new SQLException();
            String path = exchange.getRequestURI().getPath();
            GoodsHandler goodsHandler = new GoodsHandler(Integer.parseInt(path.substring(path.lastIndexOf("/")+1)));
            goodsHandler.callCommand("create");
            exchange.sendResponseHeaders(201,0);
        }
        catch (SQLException e) {
            exchange.sendResponseHeaders(409,0);

        }
        finally {
            os.close();
        }
    }

    private ArrayList<String> getParams(InputStream is) throws IOException{
        ArrayList<String> params = new ArrayList<>();
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(is);
        jsonParser.nextToken();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT || jsonParser.nextToken() != null){
            jsonParser.nextToken();
            params.add(jsonParser.getText());
        }
        jsonParser.close();
        return params;
    }

    private void changeGood(HttpExchange exchange) throws IOException{
        InputStream is = exchange.getRequestBody();

        OutputStream os = exchange.getResponseBody();

        String path = exchange.getRequestURI().getPath();

        ArrayList<String> params = null;
        try {
            params = getParams(is);
            if(Double.parseDouble(params.get(6)) < 0)
                throw new SQLException();
            GoodsHandler goodsHandler = new GoodsHandler(Integer.parseInt(path.substring(path.lastIndexOf("/")+1)));
            goodsHandler.callCommand("update");
            exchange.sendResponseHeaders(204,-1);

        } catch (SQLException e) {
            if(Integer.parseInt(params.get(5)) < 0 || Double.parseDouble(params.get(6)) < 0)
                exchange.sendResponseHeaders(409,0);
            else
                exchange.sendResponseHeaders(404,0);
        }
        finally {
            os.close();
        }
    }
}

