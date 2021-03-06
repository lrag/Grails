<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cliente JS</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <asset:javascript src="jquery-3.3.1.min.js"/>

    <g:javascript>

        var url = 'http://localhost:8080/';
        var pedidoSel;

        function listarPedidos(){
            $.ajax( { url : url+"pedidos" } ).
            done( rellenarTablaPedidos ).
            fail( procesarError );
        }

        function listarClientes(){
            $.ajax( { url : url+"clientes" } ).
            done( rellenarSelectClientes ).
            fail( procesarError );
        }

        function procesarError(error){
            console.log(error);
        }

        function rellenarTablaPedidos(pedidos){

            modoInsercion();
            vaciarFormulario();

            $("#tablaPedidos").html("");

            for(let pedido of pedidos){
                $("<tr>"+
                    "<td>"+pedido.codigo+"</td>"+
                    "<td>"+pedido.cliente.nombre+"</td>"+
                    "<td>"+pedido.fecha+"</td>"+
                    "<td>"+pedido.estado+"</td>"+
                  "</tr>")
                    .click(function(){
                        seleccionarPedido(pedido.id); //<--
                    }).
                appendTo("#tablaPedidos");
            }
        }

        function rellenarSelectClientes(clientes){

            $("#cliente").html("<option value=''>Seleccione</option>");

            for(let cliente of clientes){
                $("<option value="+cliente.id+">"+cliente.nombre+"</option>")
                    .appendTo("#cliente");
            }
        }

        function seleccionarPedido(id){
            $.ajax(
                { 'url' : url+'pedidos/'+id,
                    'success' : rellenarFormulario } );
            modoSeleccion();
        }

        function rellenarFormulario(pedido){

            pedidoSel = pedido;

            $("#codigo").val(pedido.codigo);
            $("#cliente").val(pedido.cliente.id);
            $("#fecha").val(pedido.fecha);
            $("#estado").val(pedido.estado);
        }

        function insertarPedido(){

            var pedido = {};
            $("#formulario [type=text]").each(function(){
                pedido[this.id] = this.value;
            });
            pedido.cliente = { id : cliente.value }

            //$.post
            $.ajax( { 'url'          : url+"pedidos",
                'success'      : listarPedidos,
                'error'        : procesarError,
                'type'         : 'post',
                'contentType' : 'application/json; charset=UTF-8',
                'data'         : JSON.stringify(pedido)
            } );
        }

        function modificarPedido(){

            var pedido = {};
            $("#formulario [type=text]").each(function(){
                pedido[this.id] = this.value;
            });
            pedido.cliente = { id : cliente.value }
            pedido.id = pedidoSel.id;

            $.ajax( { 'url'          : url+"pedidos/"+pedido.id,
                'success'      : listarPedidos,
                'error'        : procesarError,
                'type'         : 'put',
                'contentType' : 'application/json; charset=UTF-8',
                'data'         : JSON.stringify(pedido)
            } );
        }

        function borrarPedido(){
            $.ajax( { 'url'          : url+"pedidos/"+pedidoSel.id,
                'success'      : listarPedidos,
                'error'        : procesarError,
                'type'         : 'delete'
            } );
        }

        function vaciarFormulario(){
            $("#formulario [type=text]").val("");
            $("#cliente").val("")
            modoInsercion();
        }

        function modoInsercion(){
            $("#btnInsertar").prop("disabled", false);
            $("#btnModificar").prop("disabled", true);
            $("#btnBorrar").prop("disabled", true);
        }

        function modoSeleccion(){
            $("#btnInsertar").prop("disabled", true);
            $("#btnModificar").prop("disabled", false);
            $("#btnBorrar").prop("disabled", false);
        }

        $(inicializar);

        function inicializar(){
            $("#btnInsertar").click(insertarPedido)
            $("#btnModificar").click(modificarPedido);
            $("#btnBorrar").click(borrarPedido);
            $("#btnVaciar").click(vaciarFormulario);
            modoInsercion();
            listarPedidos();
            listarClientes();
        }

    </g:javascript>

</head>

<body>

    <div class="text-center jumbotron">
        <h1>
            Gesti??n de Pedidos
        </h1>
    </div>

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-12 col-md-8 offset-md-2">

                <div class="text-center">
                    <input class="btn btn-primary" type="button" id="btnInsertar"  value="Insertar"/>
                    <input class="btn btn-primary" type="button" id="btnModificar" value="Modificar"/>
                    <input class="btn btn-danger"  type="button" id="btnBorrar"    value="Borrar"/>
                    <input class="btn btn-primary" type="button" id="btnVaciar"    value="Vaciar"/>
                </div>

                <div class="mt-4"></div>

                <div class="form-group">
                    <label class="control-label col-xs-2" for="correoE">Correo E.</label>
                    <div class="col-xs-8">
                        <input type="text"
                               class="form-control"
                               [(ngModel)]="usuario.correoE"/>
                    </div>
                </div>

                <div class="form-horizontal" id="formulario">
                    <div class="form-group">
                        <label class="control-label col-xs-2" for="nombre">C??digo</label>
                        <div class="col-xs-8">
                            <input type="text" id="codigo" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2" for="login">Cliente</label>
                        <div class="col-xs-8">
                            <select id="cliente" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2" for="password">Fecha</label>
                        <div class="col-xs-8">
                            <input type="text" id="fecha" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2" for="password">Estado</label>
                        <div class="col-xs-8">
                            <input type="text" id="estado" class="form-control" />
                        </div>
                    </div>
                </div>

                <p/>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>C??digo</th>
                        <th>Cliente</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                    </tr>
                    </thead>
                    <tbody id="tablaPedidos">
                    </tbody>
                </table>

            </div>
            <div class="col-xs-0 col-sm-2"></div>
        </div>

    </div>

</body>
</html>

