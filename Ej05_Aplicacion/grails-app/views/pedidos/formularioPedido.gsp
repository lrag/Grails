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

    <g:form controller="pedidos" method="POST">
        <div class="text-center">
            <g:actionSubmit value="Insertar"  action="insertarPedido"  class="btn btn-primary"/>
            <g:actionSubmit value="Modificar" action="modificarPedido" class="btn btn-primary"/>
            <g:actionSubmit value="Borrar"    action="borrarPedido"    class="btn btn-primary"/>
            <button class="btn btn-primary">Vaciar</button>
        </div>

        <g:hiddenField name="id" value="${pedidoSel.id}"/>

        <div class="row">
            <div class="col-8 offset-2">
                <div class="row">
                    <div class="col-2 mt-1">Código</div>
                    <div class="col-10 mt-1">
                        <g:textField name="codigo" value="${pedidoSel.codigo}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Dirección entrega</div>
                    <div class="col-10 mt-1">
                        <g:textField name="direccion" value="${pedidoSel.direccion}" class="form-control"/>
                    </div>
                    <div class="col-2 mt-1">Fecha</div>
                    <div class="col-10 mt-1">
                        <g:datePicker name="fecha" class="form-control" value="${pedidoSel.fecha}"/>
                    </div>
                    <div class="col-2 mt-1">Cliente</div>
                    <div class="col-10 mt-1">
                        <g:select class="form-control"
                                  name="cliente.id"
                                  from="${listadoClientes}"
                                  value="${pedidoSel.cliente?.idCliente}"
                                  optionValue="nombre"
                                  optionKey="idCliente" />
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