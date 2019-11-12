<%@page import="DB.ControladorEmpleados"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Empleados.Empleado"%>
<%@page import="Empleados.Empleado"%>
<br>
<br>
<h1 style="text-align: center">Periodos Laborales</h1>

<%
    ControladorEmpleados control = new ControladorEmpleados();
     ArrayList<Empleado> empleados = control.obtenerEmpleados();
        if (request.getAttribute("RevistasAMostrarG") != null) {
            empleados = (ArrayList) request.getAttribute("RevistasAMostrarG");
        }
    %>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">CUI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Area</th>
            <th scope="col">Estado</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (int idx = 0; idx < empleados.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=empleados.get(idx).getCui()%></td>
            <td><%=empleados.get(idx).getNombre()%></td>
            <td><%=empleados.get(idx).getArea().getNombre()%></td>
            <td><%=empleados.get(idx).getPeriodos().get(0).getNombre()%></td>
              
        
        </tr>
        <%
            }%>


    </tbody>
</table>
