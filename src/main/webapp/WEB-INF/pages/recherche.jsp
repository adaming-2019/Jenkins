<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--  ajouter la taglib form de srping -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Ajouter la lib core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche</title>
<!--  lier le fichier css à ma page car boostrap ne se trouve pas dans le même fichier que ma page -->
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>"/>
</head>
<body>
<%@ include file="../../template/header.html" %>
<h1 style="color: red; text-align: center;">Formulaire de recherche</h1>

	<form method="GET" action="submitGet">
		Id : <input type="number" name="pId" /> 
		<br/> 
		<br/>
		<input class="btn btn-success" type="submit" value="Rechercher">
	</form>
	<c:if test="${not empty eGet}">
		<h1 style="color: red; text-align: center;">L'étudiant trouvé:</h1>
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date</th>
			</tr>

			<tr>
				<td>${eGet.id}</td>
				<td>${eGet.nom}</td>
				<td>${eGet.prenom}</td>
				<td>${eGet.dn}</td>
			</tr>

		</table>
	</c:if>

	<h1 style="color: red; text-align: center;">${msg}</h1>


</body>
</html>