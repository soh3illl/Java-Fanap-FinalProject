<%@ page
        import="org.example.model.Bank" %>
<%@ page
        import="org.example.model.BankAccount" %>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>Edit Account</title>
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
<div class="container mt-4">
    <h1>Edit Account</h1>
</div>
<form class="container mt-4"
      method="post"
      action="${pageContext.request.contextPath}/accounts/edit">
    <% BankAccount bankAccount= (BankAccount) request.getAttribute("bankAccount"); %>
    <div class="mb-3">
        <label for="accountNumberId" class="form-label">Account Number</label>
        <input type="text"
               class="form-control"
               id="accountNumberId"
               name="accountNumber"
               value="<%=bankAccount.getAccountNumber()%>"
               required
        >
    </div>
    <div class="mb-3">
        <label for="balanceId" class="form-label">Balance</label>
        <input type="number"
               class="form-control"
               id="balanceId"
               name="balance"
               value="<%=bankAccount.getBalance()%>"
               min="1"
               required
        >
    </div>
    <input type="hidden"
           value="<%=bankAccount.getId()%>"
           name="accountId"/>
    <input type="submit"
           class="btn btn-primary"
           value="Update Account"/>
</form>
</body>
</html>