<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes Projeto</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Detalhes do Projeto</h1>
    <div class="my-3">
        <p><strong>Nome:</strong> ${projeto.nome}</p>
        <p><strong>Data início:</strong> ${projeto.dataInicio}</p>
        <p><strong>Data prevista término:</strong> ${projeto.previsaoTermino}</p>
        <p><strong>Data real término:</strong> ${projeto.dataRealTermino}</p>
        <p><strong>Gerente Responsável:</strong> ${projeto.gerenteResponsavel}</p>
        <p><strong>Status:</strong> ${projeto.status.descricao}</p>
        <p><strong>Descrição:</strong> ${projeto.descricao}</p>
        <p><strong>Funcionários:</strong> ${funcionarios}</p>
        <a href="/projetos" class="btn btn-primary">Voltar para listagem</a>
    </div>
</div>
</body>
</html>
