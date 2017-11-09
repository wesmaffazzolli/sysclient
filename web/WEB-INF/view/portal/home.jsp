<%-- 
    Document   : home
    Created on : 07/11/2017, 13:31:44
    Author     : Wesley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${sessionScope.usuario == null}">
            <c:set var="mensagem" value="O usuário não está logado. Faça o login para prosseguir." />
            <jsp:forward page="/erro.jsp" />
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
    </head>
    <body>
        <h1>Seja bem-vindo ao Portal, ${sessionScope.nome}</h1>
        <br>
        <h2>Funcionalidades disponíveis:</h2>
        <a href="http://localhost:8081/sysclient/empresa">CRUD Empresa</a>
        <br>
        <a href="http://localhost:8081/sysclient/funcionario">CRUD Funcionário</a>
        <br>
        <br>
        <h2>Relatórios:</h2>
        <a href="http://localhost:8081/sysclient/relatorio">Relatório de Empresas</a>
        <br>
        <br>
        <a href="http://localhost:8081/sysclient/logout">Logout</a>
    </body>
</html>
