<%@page import="java.time.LocalDate"%>
<%@page import="Empleados.PeriodoLaboral"%>
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
            <th scope="col" style="color: #28a745">#</th>
            <th scope="col"style="color: #28a745">CUI</th>
            <th scope="col"style="color: #28a745">Nombre</th>
            <th scope="col"style="color: #28a745">Area</th>
            <th scope="col"style="color: #28a745">Estado</th>
            <th scope="col"></th>
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
            <%if (empleados.get(idx).getPeriodos().get(0).getNombre().equals(PeriodoLaboral.ID_CONTRATADO)) {
            %>
            <td>
                <input type="button"data-toggle="modal" data-target="#DespidoModal<%=idx%>" class="btn btn-danger"value="Despedir">



                <div class="modal fade" id="DespidoModal<%=idx%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Despido</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="/Hospital/Despedir" method="post" >
                                <div class="modal-body">
                                    <input hidden="true" name="cui" value="<%=empleados.get(idx).getCui()%>">
                                    <input hidden="true" name="usuario" value="<%=empleados.get(idx).getArea().isUsuario()%>">
                                    <a style="font: bold">CUI:</a><a ><%=empleados.get(idx).getCui()%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a style="font: bold">Nombre:</a><a><%=empleados.get(idx).getNombre()%></a>
                                    <br>
                                    <br>                                    
                                    Fecha De Despido: &nbsp;&nbsp;&nbsp; <input name="fecha" type="date" value="<%=LocalDate.now()%>">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <input type="submit" name="Despedir" class="btn btn-danger" value="Despedir"></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>



                <input type="submit" data-toggle="modal" data-target="#RenunciaModal<%=idx%>" class="btn btn-secondary" value="Renuncia">
                
                
                 <div class="modal fade" id="RenunciaModal<%=idx%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Renuncia</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="/Hospital/Despedir" method="post" >
                                <div class="modal-body">
                                    <input hidden="true" name="cui" value="<%=empleados.get(idx).getCui()%>">
                                    <input hidden="true" name="usuario" value="<%=empleados.get(idx).getArea().isUsuario()%>">
                                    <a style="font: bold">CUI:</a><a ><%=empleados.get(idx).getCui()%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a style="font: bold">Nombre:</a><a><%=empleados.get(idx).getNombre()%></a>
                                    <br>
                                    <br>                                    
                                    Fecha De Renuncia: &nbsp;&nbsp;&nbsp; <input name="fecha" type="date" value="<%=LocalDate.now()%>">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <input type="submit" name="Registrar" class="btn btn-primary" value="Registrar"></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
            </td>
            <%
                }%>
        </tr>
        <%
            }%>


    </tbody>
</table>
