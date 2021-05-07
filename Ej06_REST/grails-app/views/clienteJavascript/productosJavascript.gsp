<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente JS</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <asset:javascript src="jquery-3.3.1.min.js"/>

    <g:javascript>

        //Guardamos en esta variable el id de la película seleccionada
        var idProductoSel = null

        function procesarErrorPeticion(jqXHR){
            let status = jqXHR.status
            if(status=="401" || status=="403"){
                window.location = "login.html"
            }
        }

        function listarProductos(){
            $.ajax( { url : '/productos' })
            .done(rellenarTablaProductos)
            .fail(procesarErrorPeticion)
        }

        function rellenarTablaProductos(productos){
            var tabla = $("#tablaProductos")
            tabla.html("")

            for(let p of productos){
                $("<tr id='producto-"+p.id+"'>"+
                    "<td>"+p.nombre+"</td>"+
                    "<td>"+p.fabricante+"</td>"+
                    "<td>"+p.precio+"</td>"+
                    "<td>"+p.existencias+"</td>"+
                "</tr>")
                .click(() => seleccionarProducto(p.id))
                .appendTo(tabla)
            }
            $("#producto-"+idProductoSel).css( 'color', 'green')
        }

        function seleccionarProducto(id){
            idProductoSel = id

            $.ajax( { url : '/productos/'+id } )
            .done( (producto) => {
                rellenarFormularioProducto(producto)
                listarProductos()
                modoSeleccion()
            })
            .fail(procesarErrorPeticion)
        }

        function rellenarFormularioProducto(producto){
            $("#nombre").val(producto.nombre)
            $("#fabricante").val(producto.fabricante)
            $("#precio").val(producto.precio)
            $("#existencias").val(producto.existencias)
        }

        function insertarProducto(){
            let producto = {
                nombre      : $("#nombre").val(),
                fabricante  : $("#fabricante").val(),
                precio      : $("#precio").val(),
                existencias : $("#existencias").val()
            }
            let json = JSON.stringify(producto)

            $.ajax( {
                type        : 'post',
                url         : '/productos',
                contentType : 'application/json; charset=utf-8',
                data        : json
            } )
            .done( () => {
                vaciarFormularioProducto()
                listarProductos()
                modoInsercion()
            })
            .fail(procesarErrorPeticion)
        }

        function modificarProducto(){
            var producto = {
                id           : idProductoSel,
                nombre      : $("#nombre").val(),
                fabricante  : $("#fabricante").val(),
                precio      : $("#precio").val(),
                existencias : $("#existencias").val()
            }
            var json = JSON.stringify(producto)

            $.ajax({
                type        : 'put',
                url         : '/productos/'+idProductoSel,
                contentType : 'application/json; charset=utf-8',
                data        : json
            })
            .done( () => {
                vaciarFormularioProducto()
                listarProductos()
                modoInsercion()
            })
            .fail(procesarErrorPeticion)
        }

        function borrarProducto(){
            $.ajax( {
                type    : 'delete',
                url     : '/productos/'+idProductoSel,
                success : listarProductos
            } )
            .done( () => {
                vaciarFormularioProducto()
                listarProductos()
                modoInsercion()
            })
            .fail(procesarErrorPeticion)
        }

        function vaciarFormularioProducto(){
            $("#producto-"+idProductoSel).css( 'color', 'black')
            idProductoSel = null
            $("#nombre").val("")
            $("#fabricante").val("")
            $("#precio").val("")
            $("#existencias").val("")
            modoInsercion()
        }

        function modoInsercion(){
            $("#btnInsertar").prop("disabled",false)
            $("#btnModificar").prop("disabled",true)
            $("#btnBorrar").prop("disabled",true)
        }

        function modoSeleccion(){
            $("#btnInsertar").prop("disabled",true)
            $("#btnModificar").prop("disabled",false)
            $("#btnBorrar").prop("disabled",false)
        }

        function volver(){
            window.location = "/"
        }

        $(function(){
            $("#btnInsertar").click(insertarProducto)
            $("#btnModificar").click(modificarProducto)
            $("#btnBorrar").click(borrarProducto)
            $("#btnVaciar").click(vaciarFormularioProducto)
            $("#btnVolver").click(volver)

            $("#btnModificar").prop("disabled",true)
            $("#btnBorrar").prop("disabled",true)

            modoInsercion()
            listarProductos()
        });

    </g:javascript>

</head>

<body>

<div class="container-fluid">

    <div class="text-center jumbotron">
        <h1>
            Gestión de Productos
        </h1>
    </div>

    <div class="row">

        <div class="col-sm-12 col-md-8 offset-md-2">

            <div class="text-center">
                <input type="button" id="btnInsertar"  class="btn btn-primary" value="Insertar"/>
                <input type="button" id="btnModificar" class="btn btn-primary" value="Modificar"/>
                <input type="button" id="btnBorrar"    class="btn btn-primary" value="Borrar"/>
                <input type="button" id="btnVaciar"    class="btn btn-primary" value="Vaciar"/>
                <input type="button" id="btnVolver"    class="btn btn-primary" value="Volver"/>
            </div>

            <div class="mt-4"></div>

            <div class="row">

                <div class="col-2 mt-1">
                    <label for="nombre">Nombre</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="nombre"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="fabricante">Fabricante</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="fabricante"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="precio">Precio</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="precio"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="existencias">Existencias</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="existencias"/>
                </div>
            </div>

            <div class="mt-4"></div>

            <table class="col-12 table table-hover table-striped">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Fabricante</th>
                    <th>Precio</th>
                    <th>Existencias</th>
                </tr>
                </thead>
                <tbody id="tablaProductos"></tbody>
            </table>

        </div>

    </div>

</div>

</body>
</html>

