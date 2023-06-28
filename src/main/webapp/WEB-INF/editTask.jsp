<%--
  Created by IntelliJ IDEA.
  User: jeant
  Date: 28/06/2023
  Time: 08:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Task" %>
<%@ page import="dao.TaskDao" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Editar Tarefa</title>
</head>
<body  class="bg-dark text-white">
<div class="container">

    <%
        String taskid = request.getParameter("taskid");
        List<Task> task = TaskDao.getTaskId(request.getParameter("taskid"));
    %>
    <%
        for (int i = 0; i < task.size(); i++) {
    %>
    <h1 class="mt-5 mb-3">Editar Tarefa <%=taskid%> </h1>
        <form  action="<%= request.getContextPath() %>/editTask" method="post">
            <div class="mb-3">
                <label for="titulo" class="form-label">Título</label>
                <input type="text" class="form-control" id="titulo" value=<%=task.get(i).getTitulo()%> name="titulo" readonly="true">
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="idtask" value=<%=task.get(i).getId()%> name="idtask" hidden>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="iduser" value=<%=task.get(i).getId_user()%> name="iduser" hidden>
            </div>
            <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                <textarea class="form-control" id="descricao" value="oi mundo lindo" name="descricao"  rows="3" ><%=task.get(i).getDescricao()%></textarea>
            </div>
            <div class="mb-3">
                <label for="data_conclusao" class="form-label">Data de Conclusão</label>
                <input type="date" class="form-control" id="data_conclusao" value=<%=task.get(i).getData_conclusao()%> name="data_conclusao">
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-select" id="status"name="status" required>
                    <option value="Em andamento">Em andamento</option>
                    <option value="Concluída">Concluida</option>
                    <option value="Pendente">Pendente</option>
                </select>
            </div>
            <input class="btn btn-primary btn-sm" type="submit" value="Editar" />

        </form>
    <%
        }
    %>
    <a href="<%= request.getContextPath()%>/listTask">
        <button class="btn btn-danger btn-sm">
            Voltar
        </button>
    </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
    document.getElementById("descricao").innerHTML="valor"
</script>
</body>
</html>
