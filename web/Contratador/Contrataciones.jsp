<%@page import="java.time.LocalDate"%>
<%@page import="Empleados.Area"%>
<%@page import="Empleados.PeriodoLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Empleados.Empleado"%>
<%@page import="Empleados.Empleado"%>
<%@page import="DB.ControladorEmpleados"%>
<br>
<br>
<h1 style="text-align: center">Contrataciones</h1>
<br>

<a style="size: 30px">     Empezar Proceso De Contratacion: </a>
<button onclick="mostrarDivision()" class="btn btn-success" value="Registrar">Registrar</button>
<div id="ProcesoNuevo" hidden="true">
    <form action="/Hospital/ProcesoContratacion" method="post">

        <div class="container-fluid" style="background: #56ef38">
            <br>
            CUI: <input type="text" name="Cui" placeholder="Ingrese CUI" required="true">  &nbsp;&nbsp;&nbsp;
            Nombre: <input name="Nombre" type="text" placeholder="Ingrese Nombre" required="true">&nbsp;&nbsp;&nbsp;
            <%ControladorEmpleados controlCon = new ControladorEmpleados();
                ArrayList<Area> areas = controlCon.obtenerAreas(); %>
            Area: 
            <select name="Area"class="custom-select" style="width:15%">

                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <%
                        for (int idx = 0; idx < areas.size(); idx++) {%>
                    <option class="dropdown-item" href="#"><%=areas.get(idx).getNombre()%></option>
                    <%
                        }%>

                </div>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="Fecha"type="date" value="<%=LocalDate.now()%>">
            <input class="btn btn-primary"type="submit" value="Registrar">
            <br>
            &nbsp;
        </div>
    </form>
</div>
<br>
<br>
<h2 style="text-align: center">En Proceso De Contratacion</h2>

<br>
<%

    ArrayList<Empleado> empleadosPorPeriodo = controlCon.obtenerPorPeriodoLaboral(PeriodoLaboral.ID_PROCESO);
    if (request.getAttribute("RevistasAMostrarG") != null) {
        empleadosPorPeriodo = (ArrayList) request.getAttribute("RevistasAMostrarG");
    }
%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">CUI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Area</th>
            <th scope="col">Fecha</th>
            <th scope="col">&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (int idx = 0; idx < empleadosPorPeriodo.size(); idx++) {%>
        <tr onselect="">
            <th scope="row"><%=idx + 1%></th>
            <td><%=empleadosPorPeriodo.get(idx).getCui()%></td>
            <td><%=empleadosPorPeriodo.get(idx).getNombre()%></td>
            <td><%=empleadosPorPeriodo.get(idx).getArea().getNombre()%></td>
            <td><%=empleadosPorPeriodo.get(idx).getPeriodos().get(0).getFecha()%></td>
            <td><input type="button" data-toggle="modal" data-target="#exampleModal<%=idx%>" value="Contratar" class="btn btn-primary"></td>
    <div class="modal fade" id="exampleModal<%=idx%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Contratacion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>


                <form action="/Hospital/Contratar" method="post" >

                    <div class="modal-body">
                        <input hidden="true" name="cui" value="<%=empleadosPorPeriodo.get(idx).getCui()%>">
                        <a style="font: bold">CUI:</a><a ><%=empleadosPorPeriodo.get(idx).getCui()%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a style="font: bold">Nombre:</a><a><%=empleadosPorPeriodo.get(idx).getNombre()%></a>
                        <br>
                        <br>
                        <div class="input-group input-group-sm mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Salario</span>
                            </div>
                            <input type="text" class="form-control" name="Salario" aria-label="Sizing example input" required="true" aria-describedby="inputGroup-sizing-sm">
                        </div>
                        <div class="form-check" style="text-align: left">
                            <input class="form-check-input" type="checkbox" name="igss" value="igss" id="igss">
                            <label class="form-check-label" for="NuevaE">
                                IGSS
                            </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input class="form-check-input" name="irtra"type="checkbox" value="irtra" id="irtra">
                            <label class="form-check-label" for="NuevaE">
                                IRTRA
                            </label>
                        </div>

                        Fecha De Contratacion: &nbsp;&nbsp;&nbsp; <input name="fecha" type="date" value="<%=LocalDate.now()%>">
                    </div>

                    <%if (empleadosPorPeriodo.get(idx).getArea().isUsuario()) {%>
                    UserName: &nbsp; <input type="text" name="username" placeholder="Ingresar UserName" required="true">
                    Password: &nbsp; <input type="password" name="password" id="password" placeholder="Ingresar Password" required="true">
                    Confirmar Password: &nbsp; <input onkeyup="confirmarPassword()" type="password" id="confirmacion" placeholder="Confirmar" required="true">
                    <div style="font-size: 10px;" id="errorConfirmacion">
                    </div>  
                    <%}%>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Contratar"></button>
                    </div>

                </form>


            </div>
        </div>
    </div>

</tr>
<%
    }%>

<script type="text/javascript" src="/Hospital/js/ProcesoNuevo.js"></script>

</tbody>
</table>
<script type="text/javascript" src="/Hospital/js/confirmarPass.js"></script>


