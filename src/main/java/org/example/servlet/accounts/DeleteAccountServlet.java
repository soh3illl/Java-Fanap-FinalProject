package org.example.servlet.accounts;


import org.example.model.BankAccount;
import org.example.service.BankAccountDAO;
import org.example.service.UserDAO;
import org.example.utils.ORMConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        int id = Integer.parseInt(req.getParameter("id"));
//        HttpSession session = req.getSession();
//        String userType = (String) session.getAttribute("userType");
//        if (userType.endsWith("AccountHolder")){
            bankAccountDAO.deleteAccountById(id);
            resp.sendRedirect("showAllAccountsServlet");
//        }
//        else {
//            resp.sendRedirect("/home?error=true");
//        }
    }
}
