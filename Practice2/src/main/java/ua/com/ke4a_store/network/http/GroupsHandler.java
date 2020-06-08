package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;


//      /groups
public class GroupsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("< a href =\"/groups\">Groups</a>\n" +
                "<a href=\"/goods\">Goods</a>\n" +
                "<table class=\"table\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th scope=\"col\">Remove</th>\n" +
                "        <th scope=\"col\">ID</th>\n" +
                "        <th scope=\"col\">Group Name</th>\n" +
                "        <th scope=\"col\">Total amount</th>\n" +
                "        <th scope=\"col\">Total price</th>\n" +
                "        <th scope=\"col\">Update</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "    <c:forEach var=\"group\" items=\"${groups}\">\n" +
                "        <tr>\n" +
                "            <th scope=\"row\">\n" +
                "                <form action=\"\" method=\"post\">\n" +
                "                    <input type=\"submit\" value=\"&times;\" class=\"page-link mx-auto\">\n" +
                "                    <input type=\"hidden\" name=\"id\" value=\"${group.id}\">\n" +
                "                    <input type=\"hidden\" name=\"action\" value=\"delete\">\n" +
                "                </form>\n" +
                "            </th>\n" +
                "            <td>${group.id}</td>\n" +
                "            <td>${group.name}</td>\n" +
                "            <td>${group.getTotalCount()}</td>\n" +
                "            <td>${group.getTotalPrice()}</td>\n" +
                "            <td>\n" +
                "                <form action=\"\" method=\"post\">\n" +
                "                    <input type=\"submit\" value=\"Edit\" class=\"page-link mx-auto\">\n" +
                "                    <input type=\"hidden\" name=\"id\" value=\"${group.id}\">\n" +
                "                    <input type=\"hidden\" name=\"action\" value=\"edit\">\n" +
                "                </form>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </c:forEach>\n" +
                "    <tr>\n" +
                "        <th scope=\"row\">Total:</th>\n" +
                "        <th></th>\n" +
                "        <th></th>\n" +
                "        <th>${tcount}</th>\n" +
                "        <th>${tprice}</th>\n" +
                "        <th></th>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<div class=\"d-flex justify-content-center\">\n" +
                "<button type=\"button\" class=\"btn btn-outline-primary btn-lg\" data-toggle=\"modal\" data-target=\"#AddModal\">\n" +
                "    +\n" +
                "</button>\n" +
                "</div>\n");
        StorageServer.writeResponse(httpExchange, response.toString());
    }
}