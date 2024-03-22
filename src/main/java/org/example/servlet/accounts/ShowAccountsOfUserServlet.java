package org.example.servlet.accounts;


import org.example.model.AccountHolder;
import org.example.service.UserDAO;
import org.example.utils.ORMConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountsServlet", value = "/accounts/UserAccounts")
public class ShowAccountsOfUserServlet extends HttpServlet {
    UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        userDAO = new UserDAO(ORMConfig.getEntityManager());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("in user account +++++++++++++++++++++++++++++++++++++++++++++++++++");
        int userId = Integer.parseInt(req.getParameter("id"));
        AccountHolder user =(AccountHolder) userDAO.findUserById(userId);
        req.setAttribute("bankAccounts", user.getBankAccounts());
        req.setAttribute("userId", user.getId());
        getServletContext().getRequestDispatcher("/accounts/index.jsp").forward(req, resp);
    }
}
