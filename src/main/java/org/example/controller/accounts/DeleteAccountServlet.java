package org.example.controller.accounts;


import org.example.model.DAOs.BankAccountDAO;
import org.example.service.DataValidator;
import org.example.utils.ORMConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountDeleteServlet", value = "/accounts/delete")
public class DeleteAccountServlet extends HttpServlet {
    BankAccountDAO bankAccountDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!DataValidator.hasRequiredParams(req,"id")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form data");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
            bankAccountDAO.deleteAccountById(id);
            resp.sendRedirect("showAllAccountsServlet");
    }
}
