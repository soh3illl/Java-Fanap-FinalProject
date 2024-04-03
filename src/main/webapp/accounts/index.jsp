<%@ page import="org.example.model.BankAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
    <head>
        <title>accounts</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <script defer
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
        <%--    ICONS    --%>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body>
        <div class="container">
            <h1>List of Accounts</h1>
            <table class="table mt-4">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">account number</th>
                        <th scope="col">type</th>
                        <th scope="col">owner</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                            Collection<BankAccount> bankAccounts = (Collection<BankAccount>) request.getAttribute("bankAccounts");                            int count = 0;
                        for (BankAccount bankAccount: bankAccounts) {
                            count++;

                        %>
                        <td>
                            <input type="text" value="<%=count%>">
                        </td>
                        <td>
                            <input type="text" value="<%=bankAccount.getAccountNumber()%>">
                        </td>
                        <td>
                            <input type="text" value="<%=bankAccount.getType()%>">
                        </td>
                        <td>
                            <input type="text" value="<%=bankAccount.getAccountHolder().getFirstName()%>">
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/accounts/delete"
                                  class="btn-group"
                                  method="post">
                                <input type="hidden"
                                       name="id"
                                       value="<%= bankAccount.getId() %>">
                                <a href="${pageContext.request.contextPath}/accounts/edit?id=<%= bankAccount.getId() %>"
                                   class="btn btn-sm btn-outline-warning">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <button type="submit"
                                        class="btn btn-sm btn-outline-danger ">
                                    <i class="bi bi-x"></i>
                                </button>
                            </form>
                            <%}%>
                        </td>
                    </tr>
                </tbody>
            </table>
            <% String userType = (String) request.getSession().getAttribute("userType");
            if (userType.endsWith("AccountHolder")){
            %>
            <a href="${pageContext.request.contextPath}/accounts/create?id=<%=request.getAttribute("userId")%>"
               class="btn btn-primary">
                create new bank account
            </a>
            <%}%>
        </div>
    </body>
</html>
