<%-- 
    Document   : update
    Created on : 07/11/2017, 20:57:01
    Author     : Wesley
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Atualizar Funcionário</title>
</head>
<body>
    <h1>Atualizar Funcionário</h1>
    <form action="${contextPath}/${basePath}?action=update&id=${funcionario.id}" method="POST">
        CPF:<input type="text" name="cpf" value="${funcionario.cpf}">
        <br>
        Nome:<input type="text" name="nome" value="${funcionario.nome}">
        <br>
        Endereço:<input type="text" name="endereco" value="${funcionario.endereco}">
        <br>
        Email:<input type="text" name="email" value="${funcionario.email}">
        <br>
        Celular:<input type="text" name="celular" value="${funcionario.celular}">
        <br>
        Empresa Cod:<input type="text" name="empresaCodigo" value="${funcionario.empresaCodigo}">
        <br>
        <br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>
