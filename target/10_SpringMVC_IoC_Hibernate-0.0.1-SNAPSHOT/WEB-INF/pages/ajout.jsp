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
<title>Ajouter Etudiant</title>
<!--  lier le fichier css à ma page car boostrap ne se trouve pas dans le même fichier que ma page -->
<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.css'/>" />

</head>
<body>
	<%@ include file="../../template/header.html"%>
	<h1 style="color: red; text-align: center;">Formulaire d'ajout</h1>

	<form:form cssClass="form-horizontal" method="POST" action="submitAdd" modelAttribute="eAdd">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">Nom</label>
			<div class="col-sm-3">
				<form:input type="text" cssClass="form-control" id="inputNom"
					placeholder="nom" path="nom" />
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">Prenom</label>
			<div class="col-sm-3">
				<form:input type="text" cssClass="form-control" id="inputPrenom3"
					placeholder="prénom" path="prenom" />
			</div>
		</div>


		<div class="form-group">
			<label for="inputPassword4" class="col-sm-2 control-label">Date</label>
			<div class="col-sm-3">
				<form:input type="date" cssClass="form-control" id="inputDate"
					placeholder="date de naissance" path="dn" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-warning">Ajouter</button>
			</div>
		</div>
	</form:form>
</body>
</html>