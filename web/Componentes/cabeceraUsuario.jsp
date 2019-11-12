<%@page import="Empleados.Usuario"%>

<% Usuario persona = (Usuario) request.getSession().getAttribute("Usuario");%>
<form action="./LogOut" method="post">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top"  style="text-align:left;background-color: #507ae5;color: #29f328">
         <img src="/Hospital/Imagenes/logoHospital.png" width="50" height="50" class="rounded-circle">                
         <h2 style="color: #29f328;">Hospital SDW   &emsp;&emsp;  <%=persona.getArea().getNombre()%></h2>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">


            </ul>
            <form>
                 <a class="navbar-brand" href="#"><%=persona.getUsername()%></a>

                <input  type="submit"class="btn btn-light" value="LogOut" >

            </form>
        </div>
    </nav>
</form>   
