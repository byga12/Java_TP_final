<%@page import="java.util.List"%>
<%@page import="logic.Employee"%>
<%@page import="logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Administración de empleados</title>

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
    <%
    HttpSession mySession = request.getSession();
    String username = (String) mySession.getAttribute("username");
    if (username == null) {
        response.sendRedirect("index.jsp");
    } 
    %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Empleados</h2>
          <a href="employeeAdd.jsp"><i class="fas fa-plus content_addButton"></i></a>
        </header>

        <div class="table">
          <table>
            <tr>
              <th>Acciones</th>
              <th>Usuario</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Email</th>
              <th>D.N.I</th>
              <th>Nacionalidad</th>
              <th>Fecha de nacimiento</th>
              <th>Teléfono</th>
              <th>Dirección</th>
              <th>Puesto</th>
              <th>Sueldo</th>
            </tr>
            <%Controller control = new Controller();
                List<Employee> employeeList = control.getEmployees();
                for(Employee employee : employeeList) {
            %>
            <tr>
                <td class="action_cell">
     
                    <form action="../../SvDeleteEmployee" method="POST">
                      <input type="hidden" name="userId" value="<%=employee.getUserId()%>">
                      <button type="submit" class="action_button"><i class="fas fa-trash-alt"></i></button>
                    </form>
                    /
                    <form action="../../SvEditEmployee" method="POST">
                      <input type="hidden" name="userId" value="<%=employee.getUserId()%>">
                      <button type="submit" class="action_button"><i class="fas fa-edit"></i></button>
                    </form>
 
                </td>
                <td><%=employee.getUsername()%></td>                
                <td><%=employee.getName()%></td>
                <td><%=employee.getSurname()%></td>
                <td><%=employee.getEmail()%></td>
                <td><%=employee.getDni()%></td>
                <td><%=employee.getNationality()%></td>
                <td><%=employee.getBirthDate()%></td>
                <td><%=employee.getPhone()%></td>
                <td><%=employee.getAddress()%></td>
                <td><%=employee.getJob()%></td>
                <td><%=employee.getSalary()%></td>
            </tr>
            <%}%>
            
            
            <tr>
              <td></td>
            </tr>
          </table>
        </div>
      </div>

    </div>

  </body>
</html>
