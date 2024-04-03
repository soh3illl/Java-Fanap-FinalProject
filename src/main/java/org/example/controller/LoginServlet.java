package org.example.controller;
import org.example.model.User;
import org.example.model.DAOs.UserDAO;
import org.example.utils.ORMConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.example.service.DataValidator;
@WebServlet(name = "homeServlet", value = "/")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        userDAO = new UserDAO(ORMConfig.getEntityManager());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!DataValidator.hasRequiredParams(req,"username","password")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form data");
            return;
        }
        List<User> users = userDAO.getUserByUsernameAndPassword(username, password);
        if (!users.isEmpty()) {
            User user = users.get(0);
            HttpSession session = req.getSession();
            session.setAttribute("userType", user.getClass().getName());
            if (user.getClass().getName().endsWith("AccountHolder")){
                resp.sendRedirect("accounts/UserAccounts?id="+user.getId());
            }
            else if (user.getClass().getName().endsWith("BankEmployee")){
                resp.sendRedirect("accounts/showAllAccountsServlet");
            }

        } else {
            resp.sendRedirect("/home?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
