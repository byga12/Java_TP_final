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
    <% HttpSession mySession = request.getSession(); String username = (String)
    mySession.getAttribute("username"); if (username == null) {
    response.sendRedirect("index.jsp"); } %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Formulario de alta de empleado</h2>
        </header>

        <form action="../../SvEmployee" method="POST" class="form">
          <label for="name">Nombre</label
          ><input type="text" id="name" name="name" required />

          <label for="surname">Apellido</label
          ><input type="text" id="surname" name="surname" required />

          <label for="address">Dirección</label
          ><input type="text" id="address" name="address" required />

          <label for="dni">D.N.I</label
          ><input type="text" id="dni" name="dni" required />

          <label for="birthDate">Fecha de nacimiento</label
          ><input type="date" id="birthDate" name="birthDate" required />

          <label for="nationality">Nacionalidad</label
          ><input type="text" id="nationality" name="nationality" required />

          <label for="phone">Teléfono</label
          ><input type="text" id="phone" name="phone" required />

          <label for="email">Email</label
          ><input type="text" id="email" name="email" required />

          <label for="job">Puesto</label
          ><input type="text" id="job" name="job" required />

          <label for="salary">Salario</label
          ><input type="text" id="salary" name="salary" required />

          <label for="username">Nombre de usuario</label
          ><input type="text" id="username" name="username" required />

          <label for="password">Contraseña</label
          ><input type="password" id="password" name="password" required />

          <button type="submit" class="submit_button">Enviar datos</button>
        </form>
      </div>
    </div>
  </body>
</html>
