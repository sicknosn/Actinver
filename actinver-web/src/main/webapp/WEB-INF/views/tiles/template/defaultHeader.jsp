<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <nav class="navbar navbar-light bg-light static-top">
      <div class="container">
       
          <c:choose>
	         <c:when test = "${sessionValida ne null and sessionValida eq 'OK'}">
	          	<a class="navbar-brand" href="#">EXAMEN PRACTICO</a>
	           <a class="btn btn-primary" href="<c:url value="/logout/cerrarsesiones.htm" />">CERRAR SESION</a>
	         </c:when>
	         <c:otherwise>
	          	<a class="navbar-brand" href="<c:url value="/inicio.htm" />">PRACTICA</a>
	        	<a class="btn btn-primary" href="<c:url value="/login/relogin.htm" />">INICIAR SESION</a>
	         </c:otherwise>
	      </c:choose>
      </div>
    </nav>   
