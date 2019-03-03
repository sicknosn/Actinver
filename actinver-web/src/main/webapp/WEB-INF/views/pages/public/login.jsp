
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String tipoError = "";
System.out.println("tipoError::"+request.getParameter("tipo"));
if (request.getParameter("tipo") != null) {
	tipoError = (String) request.getParameter("tipo");
	if(tipoError != null && "'datosIncorrectos'".equals(tipoError)){
		System.out.println("Datos incorrectos desde JSP");
		tipoError = "Datos incorrectos";
	}else if(tipoError != null && "'tiempo'".equals(tipoError)){
		tipoError = "Tiempo expirado";
	}
}
%>
    <!-- Login  -->
 <header id="header" class="masthead text-white text-center">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-md-10 col-lg-8 col-xl-5 mx-auto">
			 <div class="panel">
			   <h2>Login</h2>
			   <p>Por favor inserta tu usuario y contraseña</p>
			 </div>
			 	<c:choose>
					<c:when test="${empty loginForm.nombreUsuario}">
						<form id="loginForm" method="POST" action="<%=path%>/login/getNombre.htm" class="form-horizontal" commandName="loginForm">
					</c:when>
					<c:otherwise>
						<form id="loginForm" method="POST"  action="<%=path%>/loginSecurity" id="loginForm" name="loginForm" class="form-horizontal" commandName="loginForm">
						<h6>Hola: <c:out value="${loginForm.nombreUsuario}" /></h6>
					</c:otherwise>
				</c:choose>
					<div class="alert-subtitle"></div>
					<c:if test="${not empty loginForm.errorNombre}">
						<div class="alert alert-danger">
						  <strong>Error!</strong> ${loginForm.errorNombre}
						</div>
					</c:if>
					<c:if test="${not empty error}">
						<div class="alert alert-danger">
						  <strong>Error!</strong> ${error}
						</div>
					</c:if>
					<%
						if(tipoError != null && !"".equals(tipoError.trim())){
					%>
							<div class="alert alert-danger">
							  <strong>Error!</strong> <%=tipoError%>
							</div>
					<%
						}
					%>
					<c:choose>
						<c:when test="${empty loginForm.nombreUsuario}">
							<div class="form-group">
								<input type="text" class="form-control"  id="usuario" name="usuario" autofocus="" value="${loginForm.usuario}" placeholder="Usuario" required="true">
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<input type="password" id="j_username" name="j_username" value="${loginForm.usuario}" style="display:none;"/>
								<input type="password" id="j_password" name="j_password" value="${loginForm.password}" autofocus="" class="form-control"  placeholder="Ingresa tu contraseña" required="true"> 
							</div>
						</c:otherwise>
					</c:choose>
					<div class="form-actions">
						<input type="submit"
							class="btn btn-block btn-primary btn-default" value="Entrar">
					</div>
					<div class="forgot">
						 <a class="text-white" href="#">Olvidaste tu contraseña?</a>
					</div>
			</form>
		 </div>
        </div>
      </div>
</header>
<!-- section Icons Grid -->
<section class="features-icons bg-light text-center">
      <div class="container">
        <div class="row">
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-user m-auto text-primary"></i>
              </div>
              <h3>TITULO 1</h3>
              <p class="lead mb-0">texto 1.</p>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-phone m-auto text-primary"></i>
              </div>
              <h3>TITULO 2</h3>
              <p class="lead mb-0">Texto 2.</p>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-check m-auto text-primary"></i>
              </div>
              <h3>TITULO 3</h3>
              <p class="lead mb-0">Texto 3.</p>
            </div>
          </div>
        </div>
      </div>
</section>
      
      