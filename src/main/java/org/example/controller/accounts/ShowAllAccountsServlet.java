package org.example.controller.accounts;

import org.example.model.DAOs.BankAccountDAO;
import org.example.utils.ORMConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowAllAccountsServlet", value = "/accounts/showAllAccountsServlet")
public class ShowAllAccountsServlet extends HttpServlet {

    BankAccountDAO bankAccountDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bankAccounts", bankAccountDAO.getAllAccounts());
        getServletContext().getRequestDispatcher("/accounts/index.jsp").forward(request, response);
    }

}
