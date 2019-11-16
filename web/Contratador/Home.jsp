<%-- 
    Document   : Home
    Created on : Nov 10, 2019, 2:42:02 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Hospital/css/bootstrap.min.css">
        <title>Recursos Humanos</title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"  %>
        <br>
        <br>
        <br>

        <ul class="nav nav-pills nav-fill" role="tablist" id="myTab">
            <li class="nav-item">
                <a class="nav-link active <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Periodos" role="tab" aria-controls="Periodos" aria-selected="true">Periodos Laborales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link  <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Contrataciones" role="tab" aria-controls="Contrataciones" aria-selected="false">Contrataciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Salarios" role="tab" aria-controls="Periodos" aria-selected="false">Salarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Vacaciones" role="tab" aria-controls="Vacaciones" aria-selected="false">Vacaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Reportes" role="tab" aria-controls="Reportes" aria-selected="false">Reportes</a>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active<c:if test="${requestScope['comentarios'] == true}">show active</c:if>" id="Periodos" role="tabpanel" aria-labelledby="Periodos-tab"><%@include file="PeriodosLaborales.jsp"%></div>
            <div class="tab-pane fade <c:if test="${requestScope['Suscripciones'] == true}">show active</c:if>" id="Contrataciones" role="tabpanel" aria-labelledby="Contrataciones-tab"><%@include file="Contrataciones.jsp"%></div>
            <div class="tab-pane fade <c:if test="${requestScope['Suscripciones'] == true}">show active</c:if>" id="Salarios" role="tabpanel" aria-labelledby="Salarios-tab"></div>
            <div class="tab-pane fade <c:if test="${requestScope['Likes'] == true}">show active</c:if>" id="Vacaciones" role="tabpanel" aria-labelledby="Vacaciones-tab"></div>
            <div class="tab-pane fade <c:if test="${requestScope['Ganancias'] == true}">show active</c:if>" id="Reportes" role="tabpanel" aria-labelledby="Reportes-tab"><%@include file="Reportes/MenuReportes.jsp"%></div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

    </body>
</html>
