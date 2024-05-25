<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário Projeto</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>${projeto.id == null ? 'Novo Projeto' : 'Editar Projeto'}</h1>
    <form action="/projetos" method="post">
        <input type="hidden" name="id" value="${projeto.id}" />
        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" value="${projeto.nome}" required />
        </div>
        <div class="form-group">
            <label for="dataInicio">Data início</label>
            <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${projeto.dataInicio}" required />
        </div>
        <div class="form-group">
            <label for="previsaoTermino">Data prevista término</label>
            <input type="date" class="form-control" id="previsaoTermino" name="previsaoTermino" value="${projeto.previsaoTermino}" required />
        </div>
        <div class="form-group">
            <label for="gerenteResponsavel">Gerente responsável</label>
            <input type="text" class="form-control" id="gerenteResponsavel" name="gerenteResponsavel" value="${projeto.gerenteResponsavel}" required />
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status" required>
                <c:forEach var="status" items="${todosStatus}">
                    <option value="${status}" ${status == projeto.status ? 'selected' : ''}>${status.descricao}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="descricao">Descrição</label>
            <textarea class="form-control" id="descricao" name="descricao" required>${projeto.descricao}</textarea>
        </div>
        <button type="submit" class="btn btn-success">Salvar</button>
        <a href="/projetos" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>