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
            <h1>Create new account</h1>
            <form class="mt-4" method="post" action="${pageContext.request.contextPath}/accounts/create">
                <div class="mb-3">
                    <label for="accountNumberId"
                           class="form-label">Account Number</label>
                    <input type="text"
                           class="form-control"
                           id="accountNumberId"
                           name="accountNumber"
                    >
                </div>
                <div class="mb-3">
                    <label for="amountID"
                           class="form-label">amount</label>
                    <input type="number"
                           class="form-control"
                           id="amountID"
                           name="amount"
                    >
                </div>
                <div class="mb-3">
                    <label for="accountTypeId"
                           class="form-label">type</label>
                    <select class="form-select"
                            id="accountTypeId"
                            name="accountType">
                        <option value="checking">checking</option>
                        <option value="saving">saving</option>
                    </select>
                </div>
                <input type="hidden" name="userId" value="<%=request.getParameter("id")%>">
                <button type="submit"
                        class="btn btn-primary">Submit
                </button>
            </form>
        </div>
    </body>
</html>
