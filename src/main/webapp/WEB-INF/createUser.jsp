<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<% String erroruser = (String) session.getAttribute("userExists");%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Cadastrar Usu�rio</title>
</head>
<body class="bg-dark text-white">

<div class="container">
    <h1 class="mt-5 mb-3">Cadastrar Usu�rio</h1>
    <form  action="<%= request.getContextPath() %>/createUser" method="post">
        <div class="mb-3">
            <label for="login" class="form-label">Login</label>
            <input type="text" class="form-control" id="login" name="login" required>
        </div>
        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" required></input>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="mb-3">
        </div>
        <input class="btn btn-primary btn-sm"    type="submit" value="Submit" />
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    let erroruser = '<%= erroruser %>';
    if(erroruser == 'true')
        alert("Por gentileza informe outro login e email");
</script>
</body>
</html>