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
    <c:if test="${sessionScope.usuario == null}">
        <c:set var="mensagem" value="O usuário não está logado. Faça o login para prosseguir." />
        <jsp:forward page="/erro.jsp" />
    </c:if>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action</title>
</head>
<body>
    <h1>${mensagem}</h1>
    <a href="http://localhost:8081/sysclient/empresa">Mostrar Todos</a>
</body>
</html>
