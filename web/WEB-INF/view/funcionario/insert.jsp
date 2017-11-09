<%-- 
    Document   : insert
    Created on : 07/11/2017, 14:48:59
    Author     : Wesley
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${sessionScope.usuario == null}">
            <c:set var="mensagem" value="O usuário não está logado. Faça o login para prosseguir." />
            <jsp:forward page="/erro.jsp" />
        </c:if>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Adicionar Funcionário</title>
    </head>
    <body>
        <h1>Adicionar Funcionário</h1>
        <form action="${contextPath}/${basePath}?action=add" method="POST">
            CPF:<input type="text" name="cpf">
            <br>
            Nome:<input type="text" name="nome">
            <br>
            Endereço:<input type="text" name="endereco">
            <br>
            Email:<input type="text" name="email">
            <br>
            Celular:<input type="text" name="celular">
            <br>
            Empresa Cod:<input type="text" name="empresaCodigo">
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
