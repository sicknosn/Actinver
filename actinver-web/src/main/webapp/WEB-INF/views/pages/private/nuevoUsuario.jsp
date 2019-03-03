<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<section class="bg-light text-center">
      <div class="container">
        <div class="row">
		    <div class="col-12">
		     	<span>Hola <strong>${loggedinuser}</strong>, Bienvenido a la adminstración de usuarios.</span>
		    </div>
		</div>
		<div class="row">
		    <div class="col-12">
		      <hr><br/>
		    </div>
		</div>
		<c:if test="${not empty errorGeneral}">
			<div class="alert alert-danger">
			  ${errorGeneral}
			</div>
		</c:if>
		<form id="loginForm" method="POST" action="<%=path%>/usuarios/executeNuevo.htm" class="form-horizontal" commandName="usuarioForm">
        <div class="row">
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Usuario">Usuario:</label>
					<input type="text" class="form-control" name="usuario" value="${usuarioForm.usuario}" placeholder="usuario" autocomplete="off">
					<small class="form-text text-danger">${errorUsuario}</small>
			  </div>
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Email">Email:</label>
					<input type="text" class="form-control" name="mail" value="${usuarioForm.mail}" placeholder="email" autocomplete="off">
					<small class="form-text text-danger">${errorMail}</small>
			  </div>
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Contraseña">Contraseña:</label>
					<input type="password" class="form-control" name="pass" placeholder="contraseña" autocomplete="off">
					<small class="form-text text-danger">${errorPassword}</small>
			  </div>
		</div>
		<div class="row">
		    <div class="col-12">
		      <hr>
		    </div>
		</div>
		<div class="row">
			<div class="col-12 text-right">
				<a href="<c:url value='/usuarios/usuarios.htm'/>" class="btn btn-danger custom-width">CANCELAR</a>
				<input type="submit"class="btn btn-success custom-width" value="ACEPTAR">
			</div>
		</div>
		</form>
      </div>
</section>
