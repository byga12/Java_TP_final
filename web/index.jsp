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
    <link rel="stylesheet" href="globals.css" />
    <link rel="stylesheet" href="indexStyles.css" />
  </head>
  <body>
    <div class="container">
      <form action="SvAuth" class="form" method="POST">
        <h2 class="form_title">Iniciar sesión</h2>
        <div class="form_field">
          <label for="username">Nombre de usuario</label>
          <input type="text" name="username" id="username" />
        </div>
        <div class="form_field">
          <label for="password">Contraseña</label>
          <input type="password" name="password" id="password" />
        </div>
        <button class="form_submit" type="submit">Iniciar sesión</button>
      </form>
    </div>
  </body>
</html>
