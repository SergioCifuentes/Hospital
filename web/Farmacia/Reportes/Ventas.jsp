<%@page import="DB.ControladorReportes"%>
<%@page import="Medicamento.Tipo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.ControladorFarmacia"%>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">


    <form action="/Hospital/FiltracionesFarmacia" method="post">
        Nombre: <input type="text" name="nombreMed" id="nombreMed"> &nbsp;&nbsp;
        <input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarMedicina">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="ResetearMedicina">
    </form>
</div>


<%    ArrayList<String[]> medic;
    if (request.getAttribute("MedicamentosAMostrar") != null) {
        medic = (ArrayList) request.getAttribute("MedicamentosAMostrar");
    } else {
        ControladorReportes com = new ControladorReportes();
        medic = com.obtenerMedicamentos(null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Descripcion</th>
            <th scope="col">Existentes</th>
            <th scope="col">Cantidad Minima</th>
            <th scope="col">Precio</th>
            <th scope="col">Costo</th>

        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < medic.size(); idx++) {%>
        <tr <% if (Integer.parseInt(medic.get(idx)[3])<=Integer.parseInt(medi.get(idx)[4])) {%>
            style="background-color: red"<%}%>>

            <td><%=medic.get(idx)[0]%></td>
            <td><%=medic.get(idx)[1]%></td>
            <td><%=medic.get(idx)[2]%></td>
            <td><%=medic.get(idx)[3]%></td>
            <td><%=medic.get(idx)[4]%></td>
            <td><%=medic.get(idx)[5]%></td>
            <td><%=medic.get(idx)[6]%></td>
        </tr>
        <%
            }%>


    </tbody>
</table>

<form action="/Hospital/Reportes" method="post">
    <%session.setAttribute("Array", medi);%>
    
    <input type="submit" class="btn btn-danger" name="Medicamentos"value="ImportarPDF">
</form>