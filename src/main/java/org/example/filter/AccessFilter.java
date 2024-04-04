package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/accounts/edit","/accounts/delete"})
public class AccessFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userType = (String) session.getAttribute("userType");
        if (!userType.endsWith("BankEmployee")) {
            session.setAttribute("error", "You do not have permission to access this resource.");
            resp.sendRedirect("UserAccounts?id="+session.getAttribute("userId"));
            return;
        }
        chain.doFilter(request, response);
    }

}