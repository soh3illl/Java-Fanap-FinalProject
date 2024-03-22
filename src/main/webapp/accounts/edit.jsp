<%@ page import="org.example.model.Bank" %>
<%@ page import="org.example.model.BankAccount" %>
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
            <h1>Edit Account</h1>
        </div>
        <form name="form" method="post" action="${pageContext.request.contextPath}/accounts/edit" target="_blank">
                <% BankAccount bankAccount= (BankAccount) request.getAttribute("bankAccount");%>
            account number <input type="text" name="accountNumber" value="<%=bankAccount.getAccountNumber()%>"><br><br>
            balance <input type="text" name="balance" value="<%=bankAccount.getBalance()%>"><br><br>
            <input type="hidden" value="<%=bankAccount.getId()%>" name="accountId"/>
            <input type = "submit" value = "update account" />
    </body>
</html>