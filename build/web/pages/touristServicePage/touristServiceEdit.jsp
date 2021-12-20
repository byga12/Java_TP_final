<%@page import="java.text.DateFormat"%> 
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="logic.TouristService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Modificar datos de servicio</title>
    <script
      src="https://kit.fontawesome.com/d33b7f5a1f.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="../../globals.css" />
    <link href="../../formStyles.css" rel="stylesheet" type="text/css" />
    <script
      src="../../components/navigation.js"
      type="text/javascript"
      defer
    ></script>
  </head>
  <body>
    <% HttpSession mySession = request.getSession(); 
    String username = (String) mySession.getAttribute("username"); 
    if (username == null) {
      response.sendRedirect("index.jsp"); 
    } 
    TouristService toUpdateTouristService = (TouristService) mySession.getAttribute("toUpdateTouristService"); 
    //Obtengo la fecha del servicio y la formateo
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    String strDate = dateFormat.format(toUpdateTouristService.getServiceDate()); %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Formulario de edición de servicio</h2>
        </header>

        <form action="../../SvEditTouristService" method="GET" class="form">
          <label for="name">Nombre</label
          ><input
            type="text"
            id="name"
            name="name"
            value="<%=toUpdateTouristService.getServiceName()%>"
          />

          <label for="description">Descripción</label
          ><input
            type="text"
            id="description"
            name="description"
            value="<%=toUpdateTouristService.getServiceDescription()%>"
          />

          <label for="destiny">Destino</label
          ><input
            type="text"
            id="destiny"
            name="destiny"
            value="<%=toUpdateTouristService.getServiceDestiny()%>"
          />

          <label for="date">Fecha</label
            ><input
              type="date"
              id="date"
              name="date"
              value="<%=strDate%>"
            />
  
          <label for="price">Precio</label
          ><input
            type="text"
            id="price"
            name="price"
            value="<%=toUpdateTouristService.getServicePrice()%>"
          />

          <input
            type="hidden"
            name="serviceId"
            value="<%=toUpdateTouristService.getServiceId()%>"
          />

          <button type="submit" class="submit_button">Actualizar datos</button>
        </form>
      </div>
    </div>
  </body>
</html>
