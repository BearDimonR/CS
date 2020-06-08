package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GoodsDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pName = (String) req.getSession().getAttribute("pName");
        req.getSession().setAttribute("found", new GoodsDaoImpl(new Connector()).findByName(pName));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/results.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action.equals("delete")){
            long id = Long.parseLong(req.getParameter("id"));
            new GoodsDaoImpl(new Connector()).deleteById(id);
            resp.sendRedirect("/result");
        }else {
            long id = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("currentid",id);
            req.getSession().setAttribute("edittype",2);
            resp.sendRedirect("/edit");
        }
    }
}
