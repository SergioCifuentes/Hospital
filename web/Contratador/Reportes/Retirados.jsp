<%@page import="DB.ControladorReportes"%>
<%@page import="java.util.ArrayList"%>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">


    <form action="/Hospital/FiltracionEmpleados" method="post">
        Area:    <select name="Area"class="custom-select" style="width:15%">

            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <option class="dropdown-item" href="#">Ninguna</option>
                <%
                    for (int idx = 0; idx < areas.size(); idx++) {%>
                <option class="dropdown-item" href="#"><%=areas.get(idx).getNombre()%></option>
                <%
                    }%>

            </div>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioC")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Final <input name="fin" value="<%=request.getAttribute("finC")%>"type="date">  &nbsp;&nbsp;
        <input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarRet">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="ResetearRet">
    </form>
</div>

<br>
<br>

<%    ArrayList<String[]> ret;
    if (request.getAttribute("EmpleadosAMostrarRet") != null) {
        ret = (ArrayList) request.getAttribute("EmpleadosAMostrarRet");
    } else {
        ControladorReportes com2 = new ControladorReportes();
        ret = com2.obtenerRetirados(null, null, null);
    }
%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">CUI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Salario</th>
            <th scope="col">Area</th>
            <th scope="col">Fecha De Contratacion</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < ret.size(); idx++) {%>
        <tr>

            <td><%=ret.get(idx)[0]%></td>
            <td><%=ret.get(idx)[1]%></td>
            <td><%=ret.get(idx)[2]%></td>
            <td><%=ret.get(idx)[3]%></td>
            <td><%=ret.get(idx)[4]%></td>
        </tr>
        <%
            }%>


    </tbody>
</table>

<br>
<br>

<form action="/Hospital/Reportes" method="post">
    <%session.setAttribute("Array", ret);%>
    
    <input type="submit" class="btn btn-danger"name="Retirados" value="ImportarPDF">
</form>