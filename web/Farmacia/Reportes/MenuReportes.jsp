<br>
<br>
<h1>Reportes</h1>
<select name="Area"class="custom-select" style="width:15%">

    <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
        <option class="dropdown-item" role="tab" id="contact-tab" data-toggle="tab" aria-controls="Medicamentos" aria-selected="true" href="#Medicamentos">Medicamentos</option>
        <option class="dropdown-item" role="tab" id="contact-tab" data-toggle="tab" aria-controls="Retirados" aria-selected="false" href="#Retirados">Ganancias</option>
        <option class="dropdown-item" role="tab" id="contact-tab" data-toggle="tab" aria-controls="Ventas" aria-selected="false" href="#Ventas">Ventas</option>
        
    </div>
</select>

<br>
<br>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="Medicamentos" role="tabpanel" aria-labelledby="Medicamentos-tab"><%@include file="Medicamentos.jsp"%></div>
    <div class="tab-pane fade " id="Ganancias" role="tabpanel" aria-labelledby="Ganancias-tab"><%@include file="Ganancias.jsp"%></div>
    <div class="tab-pane fade " id="Ventas" role="tabpanel" aria-labelledby="Ventas-tab"><%@include file="Ventas.jsp"%></div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
