<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--  ajouter la taglib form de srping -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Ajouter la lib core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>

<meta charset="ISO-8859-1">
<!--  lier le fichier css à ma page car boostrap ne se trouve pas dans le même fichier que ma page -->
<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.css'/>"/>
<title>Suppression</title>

</head>



<body>
<%@ include file="../../template/header.html" %>
<h1 style="color: red; text-align: center;">Formulaire de suppression</h1>

	<form method="get" action="submitDelete">
		Id : <input type="number" name="pId" /> <br />
<br/>
		<input class="btn btn-danger" type="submit" value="supprimer">
	</form>

	<h1 style="color: red; text-align: center;">${msg}</h1>

</body>
</html>