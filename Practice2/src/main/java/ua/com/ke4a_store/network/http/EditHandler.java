package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

public class EditHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StringBuilder response = new StringBuilder();
        Map<String,String> parms = StorageServer.queryToMap(httpExchange.getRequestURI().getQuery());
        response.append("<html><body>");
        response.append("<a href=\"/groups\">Groups</a><a href=\"/goods\">Goods</a>" +
                        "<form action=\"\" method=\"post\">" +
                        "<table class=\"table\"><thead><tr><th scope=\"col\">Name</th><c:if test=\"${edittype == 2}\">" +
                        "<th scope=\"col\">Group</th>" +
                        "<th scope=\"col\">Price</th>" +
                        "<th scope=\"col\">Amount</th></tr></thead><tbody>" +
                        "<td><input type=\"number\" name=\"price\" value=\"${good.price}\">\n" +
                "                    </td>\n" +
                "                    <td><input type=\"number\" name=\"count\" value=\"${good.count}\">\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        <input type=\"submit\" value=\"Save\" class=\"page-link mx-auto\">\n" +
                "                        <input type=\"hidden\" name=\"action\" value=\"save\">\n" +
                "                    </td>");
        response.append("</table>\n" +
                "</form></body></html>");
        StorageServer.writeResponse(httpExchange, response.toString());
    }
}
