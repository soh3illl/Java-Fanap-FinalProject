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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "accountUpdateServlet", value = "/accounts/edit")
public class UpdateAccountServlet extends HttpServlet {
    BankAccountDAO bankAccountDAO;
    UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        ORMConfig.init();
        bankAccountDAO = new BankAccountDAO(ORMConfig.getEntityManager());
        userDAO = new UserDAO(ORMConfig.getEntityManager());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("accountId"));

        Map<String, String[]> parameterMap = req.getParameterMap();

        Map<String, Object> updates = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String attributeName = entry.getKey();
            if (attributeName.equals("accountId")) {
                continue;
            }
            String[] attributeValues = entry.getValue();
            updates.put(attributeName, attributeValues[0]);
        }

        try {
            bankAccountDAO.updateAccount(updates, id);
            resp.sendRedirect("showAllAccountsServlet");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BankAccount account = bankAccountDAO.findAccountById(id);
//        HttpSession session = req.getSession();
//        String userType = (String) session.getAttribute("userType");
//        if (userType.endsWith("AccountHolder")){
            req.setAttribute("bankAccount", account);
            getServletContext().getRequestDispatcher("/accounts/edit.jsp").forward(req, resp);
//        }
//        else {
//            resp.sendRedirect("/home?error=true");
//        }
    }
}
