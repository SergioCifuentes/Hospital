<%-- 
    Document   : Compra
    Created on : Nov 18, 2019, 12:52:49 PM
    Author     : sergio
--%>

<%@page import="DB.ControladorFarmacia"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Medicamento.Tipo"%>
<%@page import="Medicamento.AComprar"%>
<%@page import="Medicamento.Medicina"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        Agregar Tipo De Medicamento: 
        <input type="button" data-toggle="modal" data-target="#NuevoTipoModalC" value="+" class="btn btn-primary">
        <div class="modal fade" id="NuevoTipoModalC" tabindex="-1" role="dialog" aria-labelledby="NuevoTipoCLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nuevo Medicamento</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>


                    <form action="/Hospital/RegistrarMedicamento" method="post" >

                        <div class="modal-body">

                            <br>
                            Medicamento: <input type="text" name="nombre"required="true" placeholder="Ingrese Nombre">
                            <br>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Precio A Vender</span>
                                </div>

                                <input type="text" class="form-control" name="precio" aria-label="Sizing example input" required="true" aria-describedby="inputGroup-sizing-sm">
                            </div>  
                            Descripcion:</label>
                            <textarea class="form-control" id="Descripcion"name="descripcion" rows="1"></textarea>
                            Cantidad Minima <input type="number" min="0" value="0" name="cantidadMinima">

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" value="Registrar"></button>
                        </div>

                    </form>


                </div>
            </div>
        </div>



        <%
            ControladorFarmacia controlF = new ControladorFarmacia();
            ArrayList<Tipo> medicinaAComprar = controlF.obtenerTipos();
            if (request.getAttribute("RevistasAMostrarG") != null) {
                medicinaAComprar = (ArrayList) request.getAttribute("RevistasAMostrarG");
            }
        %>

        <br>
        <br>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Medicamento</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Precio A Vender c/u</th>
                    <th scope="col">Comprar</th>
                    <th scope="col"></th>

                </tr>
            </thead>
            <tbody>
                <%
                    for (int idx = 0; idx < medicinaAComprar.size(); idx++) {%>
                    <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=medicinaAComprar.get(idx).getNombre()%></td>
            <td><%=medicinaAComprar.get(idx).getDescripcion()%></td>
            <td><%=medicinaAComprar.get(idx).getPrecio()%></td>
            <td><input type="button" data-toggle="modal" data-target="#exampleModal<%=idx%>" value="Comprar" class="btn btn-success"></td>



            <div class="modal fade" id="exampleModal<%=idx%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Comprar</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>


                        <form action="/Hospital/ComprarMedicina" method="post" >

                            <div class="modal-body">
                                <input hidden="true" name="medicamento" value="<%=medicinaAComprar.get(idx).getNombre()%>">
                                <a style="font: bold">Medicamento:</a><a ><%=medicinaAComprar.get(idx).getNombre()%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                <br>
                                <br>
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-sm">Costo</span>
                                    </div>
                                    <input type="text" class="form-control" name="costo" aria-label="Sizing example input" required="true" aria-describedby="inputGroup-sizing-sm">
                                </div>
                                Cantidad: 
                                <input type="number" min="1" value="1" name="numero">
                                <br>

                                Fecha De Compra: &nbsp;&nbsp;&nbsp; <input name="fecha" type="date" value="<%=LocalDate.now()%>">
                            </div>


                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" class="btn btn-primary" value="Comprar"></button>
                            </div>

                        </form>


                    </div>
                </div>
            </div>
            </tr>
            <%
                }%>
        </tbody>
    </table>
    <script type="text/javascript" src="/Hospital/js/manejadorCheckbox.js"></script>
</body>
</html>

