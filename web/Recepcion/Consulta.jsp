<br>
Registrar Nueva Consulta: <input type="button" data-toggle="modal" data-target="#NuevoTipoModal" value="+" class="btn btn-primary">
<div class="modal fade" id="NuevoTipoModal" tabindex="-1" role="dialog" aria-labelledby="NuevoTipoLabel" aria-hidden="true">
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
