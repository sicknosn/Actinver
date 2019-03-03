<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<c:if test="${not empty exitoUsuario}">
			<div class="alert alert-success">
		    	<strong>¡Bien hecho! </strong>${exitoUsuario}
		  	</div>
		</c:if>
		<c:if test="${not empty errorGeneral}">
			<div class="alert alert-danger">
		    	<strong>¡Error! </strong>${errorGeneral}
		  	</div>
		</c:if>
        <div class="row">
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Usuario">Usuario:</label>
					<input type="text" class="form-control" value="${valorDTO.usuario}" placeholder="usuario" disabled >
			  </div>
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Email">Email:</label>
					<input type="text" class="form-control" value="${valorDTO.mail}" placeholder="email" disabled >
			  </div>
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Contraseña">Contraseña:</label>
					<input type="password" class="form-control" value="xxxxxxxx" placeholder="contraseña" disabled>
			  </div>
		</div>
		 <div class="row">
			  <div class="col-sm-6 col-md-4 text-left">
					<label for="Estatus">Estatus:</label>
					<select class="form-control" disabled >
					  <option value="0">Selecciona..</option>
				        <option value="1" <c:if test="${valorDTO.idEstatus eq 1}">selected</c:if>>ACTIVO</option>
					  <option value="2" <c:if test="${valorDTO.idEstatus eq 2}">selected</c:if> >INACTIVO</option>
				    </select>
			  </div>
		</div>
		<div class="row">
		    <div class="col-12">
		      <hr>
		    </div>
		</div>
		
		<div class="row">
		    <div class="col-12">
		      <hr><br/>
		    </div>
		</div>
		<div class="row">
			<div class="col-12 text-right">
				<a href="<c:url value='/usuarios/usuarios.htm'/>" class="btn btn-primary custom-width">ACEPTAR</a>
				<a href="<c:url value='/usuarios/editar.htm?idUsuario=${valorDTO.idUsuario}' />" class="btn btn-success custom-width">EDITAR</a>
			</div>
		</div>
      </div>
</section>
