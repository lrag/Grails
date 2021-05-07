<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente JS</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <asset:javascript src="jquery-3.3.1.min.js"/>

    <g:javascript>

        //Guardamos en esta variable el id de la película seleccionada
        var idPeliculaSel = null

        function procesarErrorPeticion(jqXHR){
            let status = jqXHR.status
            if(status=="401" || status=="403"){
                window.location = "login.html"
            }
        }

        function enviarPeticionListarPeliculas(){
            $.ajax( { url : '/peliculas' })
            .done(rellenarTablaPeliculas)
            .fail(procesarErrorPeticion)
        }

        function rellenarTablaPeliculas(peliculas){
            var tabla = $("#tablaPeliculas")
            tabla.html("")

            for(let p of peliculas){
                $("<tr>"+
                    "<td>"+p.titulo+"</td>"+
                    "<td>"+p.director+"</td>"+
                    "<td>"+p.genero+"</td>"+
                    "<td>"+p.fechaEstreno+"</td>"+
                "</tr>")
                .click(() => seleccionarPelicula(p.id))
                .appendTo(tabla)
            }
            vaciarFormularioPelicula()
        }

        function seleccionarPelicula(id){
            $("#pelicula_"+idPeliculaSel).removeClass("success");

            idPeliculaSel = id
            $("#pelicula_"+id).addClass("success")

            $("#btnInsertar").prop("disabled",true)
            $("#btnModificar").prop("disabled",false)
            $("#btnBorrar").prop("disabled",false)

            $.ajax( { url     : '/peliculas/'+id,
                success : rellenarFormularioPelicula,
                error   : function(){ alert('ZASCA!')} } )
        }

        function rellenarFormularioPelicula(pelicula){
            $("#titulo").val(pelicula.titulo)
            $("#director").val(pelicula.director)
            $("#genero").val(pelicula.genero)
            $("#estreno").val(pelicula.fechaEstreno)
        }

        function insertarPelicula(){
            var pelicula = {
                titulo       : $("#titulo").val(),
                director     : $("#director").val(),
                genero       : $("#genero").val(),
                fechaEstreno : $("#estreno").val()
            }
            var json = JSON.stringify(pelicula)

            console.log(json)

            $.ajax( { type        : 'post',
                url         : '/peliculas',
                success     : enviarPeticionListarPeliculas,
                error       : function(){ alert("ZASCA!") },
                contentType : 'application/json; charset=utf-8',
                data        : json
            })
            .done( enviarPeticionListarPeliculas )
            .fail(function(){ alert("ZASCA!") })
        }

        function modificarPelicula(){
            var pelicula = {
                id           : idPeliculaSel,
                titulo       : $("#titulo").val(),
                director     : $("#director").val(),
                genero       : $("#genero").val(),
                fechaEstreno : $("#estreno").val()
            }
            var json = JSON.stringify(pelicula)

            $.ajax( { type        : 'put',
                url         : '/peliculas/'+idPeliculaSel,
                contentType : 'application/json; charset=utf-8',
                data        : json
            } )
            .done( enviarPeticionListarPeliculas )
            .fail(function(){ alert("ZASCA!") })
        }

        function borrarPelicula(){
            console.log("Borrar:"+idPeliculaSel)
            $.ajax( { type    : 'delete',
                url     : '/peliculas/'+idPeliculaSel,
                success : enviarPeticionListarPeliculas,
                error   : function(){ alert("ZASCA!") } } )
        }

        function vaciarFormularioPelicula(){
            $("#pelicula_"+idPeliculaSel).removeClass("success")

            $("#btnInsertar").prop("disabled",false)
            $("#btnModificar").prop("disabled",true)
            $("#btnBorrar").prop("disabled",true)

            idPeliculaSel = null
            $("#titulo").val("")
            $("#director").val("")
            $("#genero").val("")
            $("#estreno").val("")
        }

        function volver(){
            window.location = "/"
        }

        $(function(){
            $("#btnInsertar").click(insertarPelicula)
            $("#btnModificar").click(modificarPelicula)
            $("#btnBorrar").click(borrarPelicula)
            $("#btnVaciar").click(vaciarFormularioPelicula)
            $("#btnVolver").click(volver)

            $("#btnModificar").prop("disabled",true)
            $("#btnBorrar").prop("disabled",true)

            enviarPeticionListarPeliculas()
        });

    </g:javascript>

</head>

<body>

<div class="container-fluid">

    <div class="text-center jumbotron">
        <h1>
            Gestión de Películas
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
                    <label for="titulo">Título</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="titulo"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="director">Director</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="director"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="genero">Género</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="genero"/>
                </div>

                <div class="col-2 mt-1">
                    <label for="estreno">Estreno</label>
                </div>
                <div class="col-10 mt-1">
                    <input type="text" class="form-control" id="estreno"/>
                </div>
            </div>

            <div class="mt-4"><div>

            <table class="col-12 table table-hover table-striped">
                <thead>
                <tr>
                    <th>Título</th>
                    <th>Director</th>
                    <th>Genero</th>
                    <th>Estreno</th>
                </tr>
                </thead>
                <tbody id="tablaPeliculas"></tbody>
            </table>

        </div>

    </div>

</body>
</html>

