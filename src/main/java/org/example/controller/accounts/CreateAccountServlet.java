package org.example.controller.accounts;

import org.example.model.*;
import org.example.model.DAOs.BankAccountDAO;
import org.example.model.DAOs.UserDAO;
import org.example.service.DataValidator;
import org.example.utils.ORMConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountCreateServlet", value = "/accounts/create")
public class CreateAccountServlet extends HttpServlet {
    UserDAO userDAO;
    BankAccountDAO bankAccountDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        userDAO = new UserDAO(ORMConfig.getEntityManager());
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!DataValidator.hasRequiredParams(req, "userId", "accountNumber", "amount", "accountType") ||
                !DataValidator.isValidAmount(req.getParameter("amount")) ||
                !DataValidator.isAccountTypeValid(req.getParameter("accountType"))) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form data");
            return;
        }
        BankAccount bankAccount = BankAccount.createAccountBasedOnType(req.getParameter("accountType"));
        User user = userDAO.findUserById(Integer.parseInt(req.getParameter("userId")));
        bankAccount.setAccountHolder((AccountHolder) user);
        bankAccount.setAccountNumber(req.getParameter("accountNumber"));
        bankAccount.setBalance(Double.valueOf(req.getParameter("amount")));
        bankAccountDAO.createAccount(bankAccount);
        resp.sendRedirect("UserAccounts?id="+user.getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("userId", id);
        getServletContext().getRequestDispatcher("/accounts/create.jsp").forward(req, resp);
    }
}
