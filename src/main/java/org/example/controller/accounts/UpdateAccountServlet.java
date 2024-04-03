package org.example.controller.accounts;


import org.example.model.BankAccount;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<String> parameterNames = extractParameterNames(req);
        if (!DataValidator.hasRequiredParams(req, parameterNames.toArray(new String[0]))) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form data");
            return;
        }

        int id = Integer.parseInt(req.getParameter("accountId"));
        Map<String, Object> updates = extractUpdates(req.getParameterMap());
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
        req.setAttribute("bankAccount", account);
        getServletContext().getRequestDispatcher("/accounts/edit.jsp").forward(req, resp);
    }

    private Map<String, Object> extractUpdates(Map<String, String[]> parameterMap) {
        Map<String, Object> updates = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String attributeName = entry.getKey();
            if (!"accountId".equals(attributeName)) {
                updates.put(attributeName, entry.getValue()[0]);
            }
        }
        return updates;
    }

    private List<String> extractParameterNames(HttpServletRequest req) {
        List<String> parameterNames = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            parameterNames.add(entry.getKey());
        }
        return parameterNames;
    }

}
