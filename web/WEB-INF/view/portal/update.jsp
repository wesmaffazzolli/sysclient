<%-- 
    Document   : update
    Created on : 07/11/2017, 01:12:27
    Author     : Wesley
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Atualizar Empresa</h1>
        <form action="${contextPath}/${basePath}?action=update&id=${param.id}" method="POST">
            CNPJ:<input type="text" name="cnpj" value="${empresa.cnpj}">
            <br>
            Razão Social:<input type="text" name="razao_social" value="${empresa.razaoSocial}">
            <br>
            Endereço:<input type="text" name="endereco" value="${empresa.endereco}">
            <br>
            Email:<input type="text" name="email" value="${empresa.email}">
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
