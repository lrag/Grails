package com.curso.controlador

class ClienteJavascriptController {

    def productos() {
        render( view: "productosJavascript" )
    }

    def peliculas() {
        render( view: "peliculasJavascript" )
    }

    //GET /clientes
    def clientes() {
        render( view: "clientesJavascript" )
    }

    def pedidos() {
        render( view: "pedidosJavascript" )
    }

}
