<%@page import="Empleados.Empleado"%>
<%@page import="Empleados.Area"%>
<%@page import="DB.ControladorReportes"%>
<%@page import="DB.ControladorEmpleados"%>
<%@page import="java.util.ArrayList"%>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">


    <form action="/Hospital/FiltracionEmpleados" method="post">
       <select name="Area"class="custom-select" style="width:15%">

        <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
        <option class="dropdown-item" >Paciente</option>
        <option class="dropdown-item" >No Paciente</option>
    </div>
       </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarMedicos">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="ResetearMedicos">
    </form>
</div>

<br>
<br>

<%    ArrayList<Empleado> medicos;
ControladorEmpleados empl=new ControladorEmpleados();
medicos=empl.obtenerEmpleadosPorArea(Area.CONDIGO_MEDICO);
    
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">CUI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Salario</th>   
            <th scope="col">Ocupado</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < medicos.size(); idx++) {%>
        <tr>

            <td><%=medicos.get(idx).getCui()%></td>
            <td><%=medicos.get(idx).getNombre()%></td>
            <td><%=medicos.get(idx).getSalario().get(0).getMonto()%></td>
            <td>No</td>
        </tr>
        <%
            }%>


    </tbody>
</table>

<br>
<br>
<form action="/Hospital/Reportes" method="post">
    
    <input type="submit" class="btn btn-danger" name="Contratados" value="ImportarPDF">
</form>