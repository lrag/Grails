<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente JS</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <asset:javascript src="jquery-3.3.1.min.js"/>

    <g:javascript>

        //Guardamos en esta variable el id de la película seleccionada
        var clienteSel = null

        function procesarErrorPeticion(jqXHR){
            let status = jqXHR.status
            if(status=="401" || status=="403"){
                window.location = "login.html"
            }
        }

        function listarClientes(){
            $.ajax( { url : '/clientes' })
            .done(rellenarTablaClientes)
            .fail(procesarErrorPeticion)
        }

        function rellenarTablaClientes(clientes){
            var tabla = $("#tablaClientes")
            tabla.html("")

            for(let c of clientes){

                let fecha = new Date(c.fechaAlta * 1000 * 3600 * 24)
                $("<tr id='cliente-"+c.idCliente+"'>"+
                    "<td>"+c.nombre+"</td>"+
                    "<td>"+c.direccion+"</td>"+
                    "<td>"+c.telefono+"</td>"+
                    "<td>"+fecha.toLocaleDateString()+"</td>"+
                    "<td>"+c.datosBancarios?.banco+"</td>"+
                    "<td>"+c.datosBancarios?.numeroTC+"</td>"+
                "</tr>")
                .click(() => seleccionarCliente(c.idCliente))
                .appendTo(tabla)
            }
            $("#cliente-"+clienteSel?.idCliente).css( 'color', 'green')
        }

        function seleccionarCliente(id){
            $.ajax( { url : '/clientes/'+id } )
            .done( (cliente) => {
                clienteSel = cliente
                rellenarFormularioCliente(cliente)
                listarClientes()
                modoSeleccion()
            })
            .fail(procesarErrorPeticion)
        }

        function rellenarFormularioCliente(cliente){
            $("#nombre").val(cliente.nombre)
            $("#direccion").val(cliente.direccion)
            $("#telefono").val(cliente.telefono)
            $("#fechaAlta").val(new Date(cliente.fechaAlta* 1000 * 3600 * 24).toLocaleDateString())
            $("#banco").val(cliente.datosBancarios?.banco)
            $("#numeroTC").val(cliente.datosBancarios?.numeroTC)
        }

        function crearCliente(){
            let trozos = $("#fechaAlta").val().split("/");
            let fechaAlta = new Date(parseInt(trozos[2], 10),
                    parseInt(trozos[1], 10) - 1,
                    parseInt(trozos[0], 10));
            let cliente = {
                idCliente : clienteSel?.idCliente,
                nombre    : $("#nombre").val(),
                direccion : $("#direccion").val(),
                telefono  : $("#telefono").val(),
                fechaAlta : Math.floor(fechaAlta.getTime()/ (1000 * 3600 * 24)+1),
                datosBancarios : {
                    id       : clienteSel?.datosBancarios.id,
                    banco    : $("#banco").val(),
                    numeroTC : $("#numeroTC").val()
                }
            }

            return cliente
        }

        function insertarCliente(){

            let json = JSON.stringify(crearCliente())

            $.ajax( {
                type        : 'post',
                url         : '/clientes',
                contentType : 'application/json; charset=utf-8',
                data        : json
            } )
            .done( () => {
                vaciarFormularioCliente()
                listarClientes()
                modoInsercion()
            })
            .fail(procesarErrorPeticion)
        }

        function modificarCliente(){
            var json = JSON.stringify(crearCliente())

            $.ajax({
                type        : 'put',
                url         : '/clientes/'+clienteSel.idCliente,
                contentType : 'application/json; charset=utf-8',
                data        : json
            })
            .done( () => {
                vaciarFormularioCliente()
                listarClientes()
                modoInsercion()
            })
            .fail(procesarErrorPeticion)
        }

        function borrarCliente(){
            $.ajax( {
                type    : 'delete',
                url     : '/clientes/'+clienteSel.idCliente,
                success : listarClientes
            } )
            .done( () => {
                vaciarFormularioCliente()
                listarClientes()
                modoInsercion()
            })
           .fail(procesarErrorPeticion)
        }

        function vaciarFormularioCliente(){
            $("#cliente-"+clienteSel?.idCliente).css( 'color', 'black')
            clienteSel = null
            $("#nombre").val("")
            $("#direccion").val("")
            $("#telefono").val("")
            $("#fechaAlta").val("")
            $("#banco").val("")
            $("#numeroTC").val("")
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
            $("#btnInsertar").click(insertarCliente)
            $("#btnModificar").click(modificarCliente)
            $("#btnBorrar").click(borrarCliente)
            $("#btnVaciar").click(vaciarFormularioCliente)
            $("#btnVolver").click(volver)

            $("#btnModificar").prop("disabled",true)
            $("#btnBorrar").prop("disabled",true)

            modoInsercion()
            listarClientes()
        });

    </g:javascript>

</head>

<body>

<div class="container-fluid">

    <div class="text-center jumbotron">
        <h1>
            Gestión de Clientes
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
                    <label for="direccion">Dirección</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="direccion"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="telefono">Teléfono</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="telefono"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="fechaAlta">Fecha Alta</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="fechaAlta"/>
                </div>

                <div class="col-12">
                    <hr/>
                </div>

                <div class="col-2 mt-1">
                    <label for="banco">Banco</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="banco"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="numeroTC">Numero TC</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="numeroTC"/>
                </div>

            </div>

            <div class="mt-4"></div>

            <table class="col-12 table table-hover table-striped">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Fecha Alta</th>
                    <th>Banco</th>
                    <th>Numero TC</th>
                </tr>
                </thead>
                <tbody id="tablaClientes"></tbody>
            </table>

        </div>

    </div>

</div>

</body>
</html>

