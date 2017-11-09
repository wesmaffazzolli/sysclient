<%-- 
    Document   : erro
    Created on : 08/11/2017, 00:29:01
    Author     : Wesley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="POST">
            Login:<input type="text" name="login">
            <br>
            Senha:<input type="text" name="senha">
            <br>
            <br>
            <input type="submit" value="Entrar">
        </form>
        <h3>Erro: ${mensagem}</h3>
    </body>
</html>