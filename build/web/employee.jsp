<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de empleados</title>
    </head>
    <body>
        <form action="SvEmployee" method="POST">
                      
            <label for="name">Nombre</label><input type="text" id="name" name="name">
            <label for="surname">Apellido</label><input type="text" id="surname" name="surname">
            <label for="address">Dirección</label><input type="text" id="address" name="address">
            <label for="birthDate">Fecha de nacimiento</label><input type="date" id="birthDate" name="birthDate">
            <label for="nationality">Nacionalidad</label><input type="text" id="nationality" name="nationality">
            <label for="phone">Teléfono</label><input type="text" id="phone" name="phone">
            <label for="email">Email</label><input type="text" id="email" name="email">

            <label for="username">Nombre de usuario</label><input type="text" id="username" name="username">
            <label for="password">Contraseña</label><input type="password" id="password" name="password">
            
            <label for="job">Puesto</label><input type="text" id="job" name="job">
            <label for="salary">Salario</label><input type="text" id="salary" name="salary">
            
            <button type="submit">Enviar datos</button>
        </form>
    </body>
</html>
