<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<section class="bg-light text-center">
      <div class="container">
        <div class="row">
        		<div class="authbar">
					<span>Hola <strong>${loggedinuser}</strong>, Bienvenido a la adminstración de usuarios.</span>
				</div>
				<br/><br/>
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
		<form id="usuariosForm" method="POST" action="<%=path%>/usuarios/executeGuardar.htm" class="form-horizontal" commandName="usuariosForm">
	        <div class="row">
				  <div class="col-sm-6 col-md-1 text-left">
						<strong><label>#</label></strong>
				  </div>
				  <div class="col-sm-6 col-md-2 text-left">
						<strong><label>Usuario</label></strong>
				  </div>
				  <div class="col-sm-6 col-md-2 text-center">
						<strong><label>Administrador</label></strong>
				  </div>
				   <div class="col-sm-6 col-md-2 text-center">
						<strong><label>Vendedor L</label></strong>
				  </div>
				   <div class="col-sm-6 col-md-2 text-center">
						<strong><label>Vendedor P</label></strong>
				  </div>
				   <div class="col-sm-6 col-md-3 text-center">
						<strong><label>acción</label></strong>
				  </div>
			</div>
			 <c:forEach items="${usuarios}" var="user" varStatus="loop">
				 <div class="row">
				    <div class="col-12">
				      <hr>
				    </div>
				</div>
				  <div class="row">
				   <input type="hidden" class="invisible" name="idUsuario" value="${user.idUsuario}">
					   <div class="col-sm-6 col-md-1 text-left">
							<label>${(loop.index)+1}</label>
					  </div>
					  <div class="col-sm-6 col-md-2 text-left">
							<label>${user.usuario}</label>
					  </div>
					  <div class="col-sm-6 col-md-2 text-center">
							<input type="checkbox" name="permisoAdmin" value="${user.idUsuario}" class="form-check-input" <c:if test="${user.permisoAdmin eq true}">checked</c:if>>
					  </div>
					   <div class="col-sm-6 col-md-2 text-center">
							<input type="checkbox" name="permisoL" value="${user.idUsuario}" class="form-check-input" <c:if test="${user.permisoL eq true}">checked</c:if>>
					  </div>
					   <div class="col-sm-6 col-md-2 text-center">
							<input type="checkbox" name="permisoP" value="${user.idUsuario}" class="form-check-input" <c:if test="${user.permisoP eq true}">checked</c:if>>
					  </div>
					   <div class="col-sm-6 col-md-3 text-center">
					   <a href="<c:url value='/usuarios/detalle.htm?idUsuario=${user.idUsuario}' />" class="btn btn-success custom-width">DETALLE</a>
					  </div>
					</div>
			 </c:forEach>
	        <div class="row">
			    <div class="col-12">
			      <hr>
			    </div>
			</div>
	        <div class="row">
				<div class="col-12 text-right">
					<a class="btn btn-danger" href="<c:url value="/menu/inicio.htm" />">CANCELAR</a>
					<input type="submit" class="btn btn-primary" value="GUARDAR">
					<a class="btn btn-success" href="<c:url value="/usuarios/nuevo.htm" />">NUEVO</a>
				</div>
			</div>
		</form>
      </div>
</section>
