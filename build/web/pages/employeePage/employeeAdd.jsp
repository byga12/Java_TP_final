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
    <link href="styles.css" rel="stylesheet" type="text/css" />
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
        <header class="header">
          <h2 class="content_title">Formulario de alta de empleado</h2>
        </header>

        <form action="../../SvEmployee" method="POST">
          <label for="name">Nombre</label
          ><input type="text" id="name" name="name" />

          <label for="surname">Apellido</label
          ><input type="text" id="surname" name="surname" />

          <label for="address">Dirección</label
          ><input type="text" id="address" name="address" />

          <label for="dni">D.N.I</label
          ><input type="text" id="dni" name="dni" />

          <label for="birthDate">Fecha de nacimiento</label
          ><input type="date" id="birthDate" name="birthDate" />

          <label for="nationality">Nacionalidad</label
          ><input type="text" id="nationality" name="nationality" />

          <label for="phone">Teléfono</label
          ><input type="text" id="phone" name="phone" />

          <label for="email">Email</label
          ><input type="text" id="email" name="email" />

          <label for="job">Puesto</label
          ><input type="text" id="job" name="job" />

          <label for="salary">Salario</label
          ><input type="text" id="salary" name="salary" />

          <label for="username">Nombre de usuario</label
          ><input type="text" id="username" name="username" />

          <label for="password">Contraseña</label
          ><input type="password" id="password" name="password" />

          <button type="submit">Enviar datos</button>
        </form>
      </div>
    </div>
  </body>
</html>
