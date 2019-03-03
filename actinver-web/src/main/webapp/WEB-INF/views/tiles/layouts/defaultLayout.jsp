<%@ page language="java" contentType="text/html; utf-8"  pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
	<meta http-equiv="Content-Type">
	<title><tiles:getAsString name="title" /></title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>PRACTICA</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet"></link>

    <!-- Custom fonts for this template -->
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet">
     <link href="<c:url value='/static/vendor/simple-line-icons/css/simple-line-icons.css' />" rel="stylesheet">
    
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/static/css/landing-page.min.css'/>" rel="stylesheet"></link>

	 <!-- Bootstrap core JavaScript -->
    <script src="<c:url value='/static/vendor/jquery/jquery.min.js' />"></script>
    <script src="<c:url value='/static/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
    
</head>
 
<body>
		
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />
		
		
</body>
</html>