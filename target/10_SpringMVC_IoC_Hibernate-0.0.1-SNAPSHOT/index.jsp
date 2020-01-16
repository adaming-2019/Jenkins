<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PageIndex</title>
</head>

<body>
<!-- redirigier la requête vers la page Accueil au lieu d'une page vierge -->
<% response.sendRedirect("ecole/liste"); %>

</body>
</html>