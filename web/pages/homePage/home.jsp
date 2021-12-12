<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Página de inicio</title>
    <script
      src="https://kit.fontawesome.com/d33b7f5a1f.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="../../globals.css" />
    <script
      src="../../components/navigation.js"
      type="text/javascript"
      defer
    ></script>
  </head>
  <body>
    <% HttpSession mySession = request.getSession(); String username = (String)
    mySession.getAttribute("username"); if (username == null) {
    response.sendRedirect("index.jsp"); } %>
    <div class="container">
      <navigation-component></navigation-component>
      <div class="content">
        <h1>ACA VA TODOOOOO</h1>
      </div>
    </div>
  </body>
</html>
