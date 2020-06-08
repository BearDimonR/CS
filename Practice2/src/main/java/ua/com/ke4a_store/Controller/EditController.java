package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GoodsDaoImpl;
import ua.com.ke4a_store.dao.impl.GroupsDaoImpl;
import ua.com.ke4a_store.entity.Good;
import ua.com.ke4a_store.entity.GoodsGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/goodedit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = (long) req.getSession().getAttribute("currentid");
        int tp =(int)req.getSession().getAttribute("edittype");
        if(tp==2) {

            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int count = Integer.parseInt(req.getParameter("count"));
            long groupId = Long.parseLong(req.getParameter("groupId"));
            new GoodsDaoImpl(new Connector()).updateById(new Good(id, name, price, count, groupId));
            resp.sendRedirect("/goods");
        }else {
            String name = req.getParameter("name");
            new GroupsDaoImpl(new Connector()).updateById(new GoodsGroup(id,name));
            resp.sendRedirect("/groups");
        }


    }
}
