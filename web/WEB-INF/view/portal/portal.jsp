<%-- 
    Document   : portal
    Created on : 05/11/2017, 16:57:38
    Author     : Wesley
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
    </head>
    <body>
        <h1>Seja bem-vindo ao Portal, ${sessionScope.nome}</h1>
        <br>
        <h2>Buscar</h2>
        <div>
            <form action="/portal/action=find" method="POST">
                <input type="text" name="find">
                <input type="submit" value="Buscar">
            </form>
            <br>
            <a href="${contextPath}/${baseDir}/portal/insert.jsp"><input type="submit" value="Adicionar Empresa"></a>
            <br>
            <h4>Mostrar tabela com todas as empresas aqui.</h4>
        </div>
    </body>
</html>
