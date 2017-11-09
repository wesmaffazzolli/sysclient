<%-- 
    Document   : empresa
    Created on : 05/11/2017, 16:57:38
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal CRUD Empresa</title>
    </head>
    <body>
        <h1>Seja bem-vindo ao CRUD Empresa, ${sessionScope.nome}</h1>
        <a href="http://localhost:8081/sysclient/portal">Voltar para o Portal</a>
        <a href="http://localhost:8081/sysclient/logout">Logout</a>
        <br>
        <h2>Buscar</h2>
        <div>
            <form action="${contextPath}/${basePath}?action=find" method="POST">
                <input type="text" name="search">
                <input type="submit" value="Buscar">
            </form>
            <br>
            <a href="http://localhost:8081/sysclient/empresa?action=add">Adicionar Empresa</a>
            <br>
            <h2>Empresas</h2>
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
        </div>
    </body>
</html>
