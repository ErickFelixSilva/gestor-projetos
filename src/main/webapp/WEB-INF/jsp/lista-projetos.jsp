<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista Projetos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Lista Projetos</h1>
    <table class="table">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Data Início</th>
                <th>Data Prevista Término</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="projeto" items="${projetos}">
                <tr>
                    <td>${projeto.nome}</td>
                    <td>${projeto.dataInicio}</td>
                    <td>${projeto.previsaoTermino}</td>
                    <td>${projeto.status}</td>
                    <td>
                        <a href="/projetos/${projeto.id}" class="btn btn-info">View</a>
                        <a href="/projetos/editar/${projeto.id}" class="btn btn-warning">Edit</a>
                        <form action="/projetos/excluir/${projeto.id}" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-danger">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/projetos/novo" class="btn btn-primary">Adicionar Novo Projeto</a>
</div>
</body>
</html>
