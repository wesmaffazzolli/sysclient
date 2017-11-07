<%-- 
    Document   : insert
    Created on : 05/11/2017, 17:45:48
    Author     : Wesley
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
 <head>
        <title>Adicionar Empresa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Adicionar Empresa</h1>
        <form action="${contextPath}/${basePath}?action=add" method="POST">
            CNPJ:<input type="text" name="cnpj">
            <br>
            Razão Social:<input type="text" name="razao_social">
            <br>
            Endereço:<input type="text" name="endereco">
            <br>
            Email:<input type="text" name="email">
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
