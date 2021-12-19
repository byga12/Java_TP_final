<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Añadir nuevo empleado</title>
    <script
      src="https://kit.fontawesome.com/d33b7f5a1f.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="../../globals.css" />
    <link rel="stylesheet" href="../../formStyles.css" />
    <script
      src="../../components/navigation.js"
      type="text/javascript"
      defer
    ></script>
  </head>
  <body>
    <% HttpSession mySession = request.getSession();
    String username = (String)
    mySession.getAttribute("username"); 
    if (username == null) {
      response.sendRedirect("index.jsp"); 
    } %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Formulario de creación de servicio</h2>
        </header>

        <form action="../../SvTouristService" method="POST" class="form">
          <label for="name">Nombre de servicio</label
          ><input type="text" id="name" name="name" required />

          <label for="description">Descripción</label
          ><input type="text" id="description" name="description" required />

          <label for="destiny">Destino</label
          ><input type="text" id="destiny" name="destiny" required />
          
          <label for="serviceDate">Fecha</label
          ><input type="date" id="serviceDate" name="serviceDate" required />

          <label for="price">Precio</label
          ><input type="text" id="price" name="price" required />

          <button type="submit" class="submit_button">Crear servicio</button>
        </form>
      </div>
    </div>
  </body>
</html>
