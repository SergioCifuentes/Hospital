<%@page import="DummiesReportes.VentasDTO"%>
<%@page import="DummiesReportes.MedicamentosDTO"%>
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


<%    ArrayList<VentasDTO> medic;
    if (request.getAttribute("VentasAMostrar") != null) {
        medic = (ArrayList) request.getAttribute("VentasAMostrar");
    } else {
        ControladorReportes com = new ControladorReportes();
        medic = com.obtenerVentas(null, null, null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">Cui</th>
            <th scope="col">Nombre</th>
            <th scope="col">Ventas</th>

        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < medic.size(); idx++) {%>
        <tr>

            <td><%=medic.get(idx).getCui()%></td>
            <td><%=medic.get(idx).getNombre()%></td>
            <td> <a class="btn btn-secondary" data-toggle="collapse" href="#collapseComentarios<%=idx%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Ventas
                </a>
                <div class="collapse" id="collapseComentarios<%=idx%>">
                    <div class="card card-body">
                        
                        <br>
                    </div>
                </div>
            </td>


        </tr>

        <%
        }%>


    </tbody>

</table>

<form action="/Hospital/Reportes" method="post">
    <%session.setAttribute("Array", medic);%>

    <input type="submit" class="btn btn-danger" name="Medicamentos"value="ImportarPDF">
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
