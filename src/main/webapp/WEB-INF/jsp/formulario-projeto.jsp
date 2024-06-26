<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Formulário Projeto</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function formatarValorParaMoedaBRL(valor) {
            valor = (valor / 100).toLocaleString('pt-BR', {
                style: 'currency',
                currency: 'BRL'
            });
            return valor;
        }

        function formatoMonetario(input) {
            let value = input.value.replace(/\D/g, '');
            if (value) {
                input.value = formatarValorParaMoedaBRL(value);
            }
        }

        function formatarOrcamentoInicial() {
            const orcamentoInput = document.getElementById('orcamentoTotal');
            if (orcamentoInput && orcamentoInput.value) {
                let value = parseFloat(orcamentoInput.value.replace(',', '.')) * 100;
                orcamentoInput.value = formatarValorParaMoedaBRL(value);
            }
        }

        window.onload = formatarOrcamentoInicial;
    </script>
</head>
<body>
<div class="container">
    <h1>${projeto.id == null ? 'Novo Projeto' : 'Editar Projeto'}</h1>
    <form action="/projetos" method="post">
        <input type="hidden" name="id" value="${projeto.id}" />
        <div class="form-group">
            <label for="nome">Nome*</label>
            <input type="text" class="form-control" id="nome" name="nome" value="${projeto.nome}" required />
        </div>
        <div class="form-group">
            <label for="dataInicio">Data início*</label>
            <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${projeto.dataInicio}" required />
        </div>
        <div class="form-group">
            <label for="dataPrevisaoFim">Data prevista término*</label>
            <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim" value="${projeto.dataPrevisaoFim}" required />
        </div>
        <div class="form-group">
            <label for="dataRealTermino">Data real término</label>
            <input type="date" class="form-control" id="dataRealTermino" name="dataRealTermino" value="${projeto.dataFim}" />
        </div>
        <div class="d-flex flex-column form-group">
            <label for="orcamentoTotal">Valor em Reais</label>
            <input class="p-1" type="text" id="orcamentoTotal" value="${projeto.orcamento}"
                name="valorOrcamento" oninput="formatoMonetario(this)" placeholder="R$ 0,00" required>
        </div>
        <div class="form-group">
            <label for="gerente">Gerente responsável*</label>
            <select class="form-control" id="gerente" name="gerente" required ${gerentes.isEmpty() ? 'disabled' : ''}>
                <c:forEach var="gerente" items="${gerentes}">
                    <option value="${gerente.id}" ${gerente.id == projeto.gerente.id ? 'selected' : ''}>${gerente.nome}</option>
                </c:forEach>
            </select>
            <c:if test="${membros.isEmpty()}">
                <small class="text-danger">Nenhum membro disponível no cadastro.</small>
            </c:if>
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
            <label for="risco">Nível de risco</label>
            <select class="form-control" id="risco" name="risco" required>
                <c:forEach var="risco" items="${riscos}">
                    <option value="${risco}" ${risco == projeto.risco ? 'selected' : ''}>${risco.descricao}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="descricao">Descrição</label>
            <textarea class="form-control" id="descricao" name="descricao">${projeto.descricao}</textarea>
        </div>
        <div class="form-group">
            <label for="funcionarios">Funcionários</label>
            <br>
            <c:forEach var="funcionario" items="${funcionarios}">
                <input type="checkbox" name="funcionarios" value="${funcionario.id}" <c:if test="${funcionario.selecionado}">checked</c:if>>
                    ${funcionario.nome}<br>
                </input>
            </c:forEach>
            <c:if test="${funcionarios.isEmpty()}">
                <small class="text-danger">Nenhum funcionário disponível no cadastro.</small>
            </c:if>
        </div>
        <button type="submit" class="btn btn-success" ${membros.isEmpty() ? 'disabled' : ''}>Salvar</button>
        <a href="/projetos" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
