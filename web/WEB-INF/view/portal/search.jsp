<%-- 
    Document   : grid
    Created on : 06/11/2017, 14:30:31
    Author     : Wesley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar</title>
    </head>
    <body>
        <table style="width:100%; text-align:left;" border="1">
            <tr>
                <th>CNPJ</th>
                <th>Razão Social</th>
                <th>Endereço</th>
                <th>Email</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="empresa" items="${lista}">
                <tr bgcolor="#${empresa.id%2==0 ? 'C0C0C0' : 'ffffff'}">
                    <td>${empresa.cnpj}</td>
                    <td>${empresa.razaoSocial}</td>
                    <td>${empresa.endereco}</td>
                    <td>${empresa.email}</td>
                    <td>
                        <a href="${contextPath}/${basePath}?action=delete&id=${empresa.id}">Remover</a>
                        &nbsp&nbsp
                        <a href="${contextPath}/${basePath}?action=update&id=${empresa.id}">Alterar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="http://localhost:8081/sysclient/portal?action=findAll">Mostrar Todos</a>
    </body>
</html>
