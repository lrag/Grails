<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

<div class=" container-fluid">

    <div class="text-center jumbotron mb-0">
        <h2>Aplicacion de pedidos</h2>
    </div>

    <div class="navbar navbar-expand-sm bg-dark navbar-dark">
        <ul class="navbar-nav">
            <li class="nav-item" routerLinkActive="active">
                <g:link controller="clientes" action="verClientes" class="nav-link">Clientes</g:link>
            </li>
            <li class="nav-item" routerLinkActive="active">
                <g:link controller="pedidos" action="listadoPedidos" class="nav-link">Pedidos</g:link>
            </li>
        </ul>
    </div>

    <br/>

    <g:form controller="clientes" method="POST">
        <div class="text-center">
            <g:actionSubmit value="Insertar"  action="insertar"  class="btn btn-primary"/>
            <g:actionSubmit value="Modificar" action="modificar" class="btn btn-primary"/>
            <g:actionSubmit value="Borrar"    action="borrar"    class="btn btn-primary"/>
            <button class="btn btn-primary">Vaciar</button>
        </div>

        <g:hiddenField name="idCliente" value="${clienteSel.idCliente}"/>

        <div class="row">
            <div class="col-8 offset-2">
                <div class="row">
                    <div class="col-2 mt-1">Nombre</div>
                    <div class="col-10 mt-1">
                        <g:textField name="nombre" value="${clienteSel.nombre}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Direccion</div>
                    <div class="col-10 mt-1">
                        <g:textField name="direccion" value="${clienteSel.direccion}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Teléfono</div>
                    <div class="col-10 mt-1">
                        <g:textField name="telefono" value="${clienteSel.telefono}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Banco</div>
                    <div class="col-10 mt-1">
                        <g:textField name="datosBancarios.banco" value="${clienteSel.datosBancarios?.banco}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Numero TC</div>
                    <div class="col-10 mt-1">
                        <g:textField name="datosBancarios.numeroTC" value="${clienteSel.datosBancarios?.numeroTC}" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </g:form>

    <div class="row">
        <table class="table table-stripped table-hover col-8 offset-2 mt-3">
            <tr>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
                <th>Banco</th>
            </tr>
            <g:each in="${listadoClientes}" var="c">
                <tr>
                    <td>
                        <g:link controller="clientes" action="seleccionarCliente" params="[id:c.idCliente]">${c.nombre}</g:link>
                    </td>
                    <td>${c.direccion}</td>
                    <td>${c.telefono}</td>
                    <td>${c.datosBancarios?.banco}</td>
                </tr>
            </g:each>
        </table>
    </div>

</div>

</body>
</html>