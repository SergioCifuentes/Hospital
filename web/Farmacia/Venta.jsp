<%@page import="java.time.LocalDate"%>
<%@page import="DB.ControladorFarmacia"%>
<%@page import="Medicamento.Tipo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Medicamento.AVender"%>
<br>
Agregar Venta:
<input type="button" data-toggle="modal" data-target="#NuevoTipoModal" value="+" class="btn btn-primary">
<div class="modal fade" id="NuevoTipoModal" tabindex="-1" role="dialog" aria-labelledby="NuevoTipoLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nuevo Medicamento</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%

                ArrayList<AVender> medicinaAVender = new ArrayList<>();
                if (request.getAttribute("medicinaAVender") != null) {
                    medicinaAVender = (ArrayList<AVender>) request.getAttribute("medicinaAVender");

                }
            %>
            <%session.setAttribute("MedicamentosAVender", medicinaAVender);%>

            <form action="/Hospital/AgregarAListaVentas" method="post" >

                <div class="modal-body">
                    <%ControladorFarmacia cofa = new ControladorFarmacia();
                        ArrayList<Tipo> tipos = cofa.obtenerTipos(); %>
                    <br>
                    Tipo <select name="Area"class="custom-select" style="width:45%">

                        <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            <%for (int idx = 0; idx < tipos.size(); idx++) {%>
                            <option class="dropdown-item" role="tab" id="contact-tab" data-toggle="tab" aria-controls="Medicamentos<%=idx%>" aria-selected="true" href="#Medicamentos<%=idx%>"><%=tipos.get(idx).getNombre()%></option>

                            <%
                                }%>

                        </div>
                    </select>

                    <br>
                    <br>
                    <div class="tab-content" id="myTabContent">
                        <%for (int idx = 0; idx < tipos.size(); idx++) {%>
                        <div class="tab-pane fade <%if (idx == 0) {%> show active<%}%>

<%
                            int cantidadExistente = cofa.obtenerMedicamentosPorTipo(tipos.get(idx).getCodigo()).size();
                            for (int j = 0; j < medicinaAVender.size(); j++) {
                                    if (tipos.get(idx).getNombre().equals(medicinaAVender.get(j).getTipo().getNombre())) {
                                        System.out.println(medicinaAVender.get(j).getCantidad());
                                            cantidadExistente=cantidadExistente-medicinaAVender.get(j).getCantidad();
                                        }
                                }
                             %>

                             " id="Medicamentos<%=idx%>" role="tabpanel" aria-labelledby="Medicamentos<%=idx%>-tab">CantidadExistente: <%=cantidadExistente%>
                            <br>
                            Cantidad A Comprar <input type="number" min="0" max="<%=cantidadExistente%>" value="0" name="cantidadAComprar">
                        </div>
                            <input hidden="true" name="nombreMedicamento"value="<%=tipos.get(idx).getNombre()%>">   
                        <%
                            }%>
                    </div>



                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Registrar"></button>
                </div>

            </form>


        </div>
    </div>
</div>




<br>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Medicamento</th>
            <th scope="col">Cantidad</th>
            <th scope="col">Precio A Vender c/u</th>
            <th scope="col">Total</th>
            <th scope="col"></th>

        </tr>
    </thead>
    <tbody>
        <%
            for (int idx = 0; idx < medicinaAVender.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=medicinaAVender.get(idx).getTipo().getNombre()%></td>
            <td><%=medicinaAVender.get(idx).getCantidad()%></td>
            <td><%=medicinaAVender.get(idx).getTipo().getPrecio()%></td>

            <td><%=medicinaAVender.get(idx).getCantidad() * medicinaAVender.get(idx).getTipo().getPrecio()%></td>


        </tr>
        <%
            }%>
    </tbody>
</table>

<div class="container" style="text-align: right">
    <%float total = 0;
        for (int idx = 0; idx < medicinaAVender.size(); idx++) {
            total = total + medicinaAVender.get(idx).getCantidad() * medicinaAVender.get(idx).getTipo().getPrecio();

        }
    %>
    <h2>Total: <%=total%></h2> 
    <br>
    <form action="/Hospital/RegistrarVenta" method="post">
    <input <%if (medicinaAVender.size() == 0) {%>disabled="true"<%}%> type="submit" class="btn badge-danger" value="Cancelar">
    
        <input <%if (medicinaAVender.size() == 0) {%>disabled="true"<%}%> type="button" data-toggle="modal" data-target="#venta" value="Vender" class="btn btn-primary">
        <div class="modal fade" id="venta" tabindex="-1" role="dialog" aria-labelledby="NuevoTipoLabel" aria-hidden="true">
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
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">NIT</span>
                                </div>

                                <input type="text" class="form-control" name="precio" aria-label="Sizing example input" required="true" aria-describedby="inputGroup-sizing-sm">
                            </div>  
                            
                            Fecha <input type="date" name="fecha"value="<%=LocalDate.now()%>">

                        </div>

                        <div class="modal-footer">
                            Total:<%=total %>&nbsp;&nbsp;
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" name="Registrar" value="Registrar"></button>
                        </div>

                    </form>


                </div>
            </div>
        </div>
    </form>
</div>
