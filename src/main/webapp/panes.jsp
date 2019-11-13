<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Panadaeria Ria</title>
</head>
<body>
	<h2>Lista de panes <a href="pan">Nuevo pan</a></h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Descripcion</th>
			<th>Tamaño</th>
			<th>Precio</th>
			<th>Fecha</th>
			<th>Acciones</th>
		</tr>
		<c:forEach var="pan" items="${listaPanes}">
			<tr>
				<td>${pan.getId()}</td>
				<td>${pan.getNombre()}</td>
				<td>${pan.getDescripcion()}</td>
				<td>${pan.getTamanyo()}</td>
				<td>${pan.getPrecio()}</td>
				<td>${pan.getFecha()}</td>
				<td>
					<form action="pan/actualizar">
						<input type="hidden" name="txtId" value="${pan.getId()}">
						<input type="submit" >
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
