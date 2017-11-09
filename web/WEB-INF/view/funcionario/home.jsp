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
        <title>Portal CRUD Funcionário</title>
    </head>
    <body>
        <h1>Seja bem-vindo ao CRUD Funcionário, ${sessionScope.nome}</h1>
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
            <a href="http://localhost:8081/sysclient/funcionario?action=add">Adicionar Funcionario</a>
            <br>
            <h2>Funcionários</h2>
            <table style="width:100%; text-align:left;" border="1">
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>Email</th>
                    <th>Celular</th>
                    <th>Empresa Cód.</th>
                    <th>Ação</th>
                </tr>
                <c:forEach var="funcionario" items="${lista}">
                    <tr bgcolor="#${funcionario.id%2==0 ? 'C0C0C0' : 'ffffff'}">
                        <td>${funcionario.cpf}</td>
                        <td>${funcionario.nome}</td>
                        <td>${funcionario.endereco}</td>
                        <td>${funcionario.email}</td>
                        <td>${funcionario.celular}</td>
                        <td>${funcionario.empresaCodigo}</td>
                        <td> 
                            <a href="${contextPath}/${basePath}?action=delete&id=${funcionario.id}">Remover</a>
                            &nbsp&nbsp
                            <a href="${contextPath}/${basePath}?action=update&id=${funcionario.id}">Alterar</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
