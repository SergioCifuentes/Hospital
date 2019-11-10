<%-- 
    Document   : Login
    Created on : Nov 8, 2019, 2:45:10 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital</title>
        <link rel="stylesheet" href="/Hospital/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="/Componentes/CabeceraInicio.html"  %>

        <br>
        <div style="background-color:white " >
            <div style="background-color: white;  width:30%;  " class="container">
                <h2 style="text-align: center;color: #424040;width:60%">Iniciar Sesion</h2>
                <form action="/Hospital/InicioSesion" method="post" >
                    UserName: <br>
                    <input type="text" name="userName" id="user"placeholder="Ingrese UserName"
                           required  
                           <br><br>

                    Password: <br>
                    <input type="password" name="pass" placeholder="Ingrese Password" required/><br>

                    <small class="form-text text-muted" style="color:red">Password Incorecto </small>

                    <br>
                    <input type="submit" class="btn btn-dark" value="Iniciar Sesion" >
                </form >
                <br>
                <div style="text-align: center;width:65%">

                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="/Hospital/js/bootsrap.min.js"></script> 

    </body>
</html>
