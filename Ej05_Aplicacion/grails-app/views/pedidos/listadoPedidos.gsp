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

    <g:form controller="pedidos" method="GET">
        <div class="text-center">
            <g:actionSubmit value="Nuevo"  action="formularioPedido"  class="btn btn-primary"/>
        </div>
    </g:form>

    <div class="row">
        <table class="table table-stripped table-hover col-8 offset-2 mt-3">
            <tr>
                <th>Codigo</th>
                <th>Fecha</th>
                <th>Cliente</th>
            </tr>
            <g:each in="${listadoPedidos}" var="p">
                <tr>
                    <td>
                        <g:link controller="pedidos" action="seleccionarPedido" params="[id:p.id]">${p.codigo}</g:link>
                    </td>
                    <td>${p.fecha}</td>
                    <td>${p.cliente.nombre}</td>
                </tr>
            </g:each>
        </table>
    </div>

</div>

</body>
</html>