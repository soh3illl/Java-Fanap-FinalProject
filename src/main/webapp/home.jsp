<%@ page
        import="java.util.Objects" %>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
    <head>
        <title>Sign in</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
              crossorigin="anonymous">
        <!-- Custom styles for this template -->
        <style>
            html,
            body {
                height: 100%;
            }

            body {
                display: -ms-flexbox;
                display: -webkit-box;
                display: flex;
                -ms-flex-align: center;
                -ms-flex-pack: center;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: center;
                justify-content: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                width: 100%;
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
            }

            .form-signin .checkbox {
                font-weight: 400;
            }

            .form-signin .form-control {
                position: relative;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
            }

            .form-signin .form-control:focus {
                z-index: 2;
            }

            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }

            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        </style>
    </head>

    <body class="text-center">
        <form class="form-signin"
              method="post"
              action="${pageContext.request.contextPath}/">
            <h1 class="h3 mb-3 font-weight-normal"
                id="title">Please sign in</h1>
            <label for="inputUsername"
                   class="sr-only">Email address</label>
            <input type="text"
                   name="username"
                   id="inputUsername"
                   class="form-control"
                   placeholder="username"
                   required
                   autofocus>
            <label for="inputPassword"
                   class="sr-only">Password</label>
            <input type="password"
                   name="password"
                   id="inputPassword"
                   class="form-control"
                   placeholder="Password"
                   required>
            <button class="btn btn-lg btn-primary btn-block"
                    type="submit">Sign in
            </button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
                crossorigin="anonymous"></script>
        <script>
            const template = `
           <div class="alert alert-danger" role="alert">
                Error ! wrong username or password
            </div>
            `;

            if (/'*error'/.test(window.local.href)) {
                const form = document.querySelector(".form-signin");
                form.insertAdjacentHTML("afterbegin", template);
            }
        </script>
    </body>
</html>
