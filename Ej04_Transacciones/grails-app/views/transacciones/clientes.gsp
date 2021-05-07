<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<script>
    function prueba(){
        alert("FU")
    }
</script>

<body>

<div class=" container-fluid">

    <div class="text-center jumbotron">
        <h2>Transacciones</h2>
    </div>

    <g:form controller="transacciones" method="POST">
        <div class="text-center">
            <g:actionSubmit value="Clientes" action="insertarClientes"  class="btn btn-primary"/>
            <g:actionSubmit value="Borrar" action="borrarClientes"  class="btn btn-primary"/>
            <button class="btn btn-primary" onclick="prueba()">Vaciar</button>
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
                    <td>${c.nombre}</td>
                    <td>${c.direccion}</td>
                    <td>${c.telefono}</td>
                </tr>
            </g:each>
            <g:if test="${listadoClientes.size()==0}">
                <tr>
                    <td colspan="3">
                        No hay clientes en la base de datos
                    </td>
                </tr>
            </g:if>
        </table>
    </div>

</div>

</body>
</html>