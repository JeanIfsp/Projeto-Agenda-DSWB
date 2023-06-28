<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="java.util.List"%>
<%@ page import="dao.TaskDao"%>
<%@ page import="model.Task" %>
<% String login = (String) session.getAttribute("login"); %>
<% String userid = (String) session.getAttribute("iduser"); %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Lista de Tarefas</title>
</head>
<body class="bg-dark text-white">

<div class="container">
    <div class="d-flex justify-content-between align-items-center mt-3">
        <h1>Lista de Tarefas da <%=login%></h1>
        <a href="./logout">
            <button class="btn btn-primary">Sair</button>
        </a>
    </div>
    <table class="table table-dark table-striped mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Descrição</th>
            <th>Data de Criação</th>
            <th>Data de Conclusão</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            String iduser =(String)session.getAttribute("userid");
            List<Task> taskList = TaskDao.getTask(iduser);
        %>
        <%
            for (int i = 0; i < taskList.size(); i++) {
        %>
            <tr>
                <td><%=taskList.get(i).getId()%></td>
                <td><%=taskList.get(i).getTitulo()%></td>
                <td><%=taskList.get(i).getDescricao()%></td>
                <td><%=taskList.get(i).getData_criacao()%></td>
                <td><%=taskList.get(i).getData_conclusao()%></td>
                <td><%=taskList.get(i).getStatus()%></td>
                <td>
                    <a class="btn btn-primary btn-sm" href="./editTask?userid=<%=taskList.get(i).getId_user()%>&taskid=<%=taskList.get(i).getId()%>">Editar</a>
                    <a class="btn btn-danger btn-sm" href="./deleteTask?taskid=<%=taskList.get(i).getId()%>">Cancelar</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div class="text-end">
        <a href="./createTask">
            <button class="btn btn-primary">
                Nova Tarefa
            </button>
        </a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
