<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Pan</title>
</head>
<body>
	<h2>Nuevo pan</h2>
	<form action="${action}" method="post">
		<input type="hidden" name="txtId" value="${pan.getId()}">
		<div>
			<label for="txtNombre">Nombre</label>
			<input type="text" id="txtNombre" name="txtNombre" value="${pan.getNombre()}">
		</div>
		<div>
			<label for="txtDes">Descripcion</label>
			<input type="text" id="txtDes" name="txtDes" value="${pan.getDescripcion()}">
		</div>
		<div>
			<label for="txtTamanyo">Tamaño</label>
			<input type="text" id="txtTamanyo" name="txtTamanyo" value="${pan.getTamanyo()}">
		</div>
		<div>
			<label for="txtPrecio">Precio</label>
			<input type="text" id="txtPrecio" name="txtPrecio" value="${pan.getPrecio()}">
		</div>
		<input type="submit" value="${actionMessage}">
	</form>
</body>
</html>