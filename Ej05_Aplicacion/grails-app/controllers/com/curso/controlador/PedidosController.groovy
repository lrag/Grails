package com.curso.controlador

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.Pedido
import com.curso.modelo.negocio.PedidosService

class PedidosController {

    PedidosService pedidosService

    def index() { }

    def listadoPedidos() {
        render( view : "listadoPedidos",
                model : [
                        listadoPedidos : Pedido.findAll()
                ])
    }

    def formularioPedido() {
        render( view : "formularioPedido",
                model : [
                        pedidoSel : new Pedido(),
                        listadoClientes : Cliente.findAll()
                ])
    }

    def seleccionarPedido() {
        Long id = params.long("id")

        println "seleccionarPedido:"+id

        render( view : "formularioPedido",
                model : [
                        pedidoSel : Pedido.findById(id),
                        listadoClientes : Cliente.findAll()
                ])
    }

    def insertarPedido() {
        Pedido pedido = new Pedido(params)
        pedidosService.insertarPedido(pedido)
        redirect([controller:'pedidos', action:'seleccionarPedido', params:[id:pedido.id]])
    }

    def modificarPedido(){

    }

    def borrarPedido(){
        Pedido pedido = new Pedido(params)
        pedidosService.borrarPedido(pedido)
        redirect([controller: 'pedidos', action: 'listadoPedidos'])
    }

    def insertarDetalle() {

    }


}
