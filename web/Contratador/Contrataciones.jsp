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
    <form>
        Nombre <input type="text" placeholder="Ingrese Nombre">
    </form>
</div>
<br>
<br>
<h2 style="text-align: center">En Proceso De Contratacion</h2>

<br>
<%
    ControladorEmpleados controlCon = new ControladorEmpleados();
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


        </tr>
        <%
            }%>

    <script type="text/javascript" src="/Hospital/js/ProcesoNuevo.js"></script>

</tbody>
</table>


