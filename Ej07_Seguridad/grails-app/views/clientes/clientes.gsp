<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

<div class=" container-fluid">

    <div class="text-center jumbotron">
        <h2>Gestión de clientes (GORM el Gormeriano)</h2>
    </div>

    <g:form controller="clientes" method="POST">
        <div class="text-center">
            <g:actionSubmit value="Insertar"  action="insertar"  class="btn btn-primary"/>
            <g:actionSubmit value="Modificar" action="modificar" class="btn btn-primary"/>
            <g:actionSubmit value="Borrar"    action="borrar"    class="btn btn-primary"/>
            <button class="btn btn-primary">Vaciar</button>
        </div>

        <g:hiddenField name="idCliente" value="${clienteSel.idCliente}"/>

        <g:renderErrors bean="${clienteSel}" />

        <div class="row">
            <div class="col-8 offset-2">
                <div class="row">
                    <div class="col-2 mt-1">Nombre</div>
                    <div class="col-10 mt-1">
                        <g:textField name="nombre" value="${clienteSel.nombre}" class="form-control ${hasErrors(bean:clienteSel,field:'nombre','is-invalid')}"/>
                        <g:hasErrors bean="${clienteSel}" field="nombre">
                            <g:eachError bean="${clienteSel}" field="nombre">
                                <p style="color: #ff0000;"><g:message error="${it}"/></p>
                            </g:eachError>
                        </g:hasErrors>
                    </div>
                    <div class="col-2 mt-1">Direccion</div>
                    <div class="col-10 mt-1">
                        <g:textField name="direccion" value="${clienteSel.direccion}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Teléfono</div>
                    <div class="col-10 mt-1">
                        <g:textField name="telefono" value="${clienteSel.telefono}" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </g:form>

    <g:form controller="logout">
        <g:submitButton name="logout" value="Logout" />
    </g:form>

    <div class="row">
        <table class="table table-stripped table-hover col-8 offset-2 mt-3">
            <tr>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
            </tr>
            <g:each in="${listadoClientes}" var="c">
                <tr>
                    <td>
                        <g:link controller="clientes" action="seleccionarCliente" params="[id:c.idCliente]">${c.nombre}</g:link>
                    </td>
                    <td>${c.direccion}</td>
                    <td>${c.telefono}</td>
                </tr>
            </g:each>
        </table>
    </div>

</div>

</body>
</html>