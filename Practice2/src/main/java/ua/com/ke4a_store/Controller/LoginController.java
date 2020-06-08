package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.UserDaoImpl;
import ua.com.ke4a_store.entity.User;
import ua.com.ke4a_store.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> user = new UserServiceImpl(new UserDaoImpl(new Connector())).findByLoginAndPassword(login,password);
        if(user.isPresent()){
            req.getSession().setAttribute("user",user.get());
            req.getSession().setAttribute("isLogged",true);
            resp.sendRedirect("/groups");
        }
        else {
            resp.sendRedirect("/");
        }
    }
}
