<%-- 
    Document   : form
    Created on : 30/09/2017, 21:10:44
    Author     : Lucas
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="ERROR" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><c:out value="${title}" /></title>
    </head>
    <body>
        <h1>Erro</h1>
        <h3>${mensagem}</h3>
        
        <a href="${contextPath}/ControllerPortal">Voltar</a>
    </body>
</html>