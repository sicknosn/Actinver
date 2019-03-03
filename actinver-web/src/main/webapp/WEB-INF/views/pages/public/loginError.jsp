<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <!-- Login  -->
 <header id="header" class="masthead text-white text-center">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-md-10 col-lg-8 col-xl-5 mx-auto">
			 <div class="panel">
			   <h2>Acceso Denegado</h2>
			   <div class="alert-subtitle"></div>
				<div class="alert alert-danger">
				   No cuenta con los permisos suficientes para ver este módulo.
				</div>
			 </div>
			 <div class="form-actions">
				<a class="btn btn-danger" href="<c:url value="/menu/inicio.htm" />">INICIO</a>
			</div>
		 </div>
        </div>
      </div>
</header>
<!-- section Icons Grid -->
<section class="features-icons bg-light text-center">
      <div class="container">
      </div>
</section>
      
      