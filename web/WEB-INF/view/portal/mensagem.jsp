<%-- 
    Document   : delete
    Created on : 06/11/2017, 22:31:17
    Author     : Wesley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete</title>
    </head>
    <body>
        <h1>${mensagem}</h1>
        <br>
        <a href="http://localhost:8081/sysclient/portal?action=findAll">Mostrar Todos</a>
    </body>
</html>
