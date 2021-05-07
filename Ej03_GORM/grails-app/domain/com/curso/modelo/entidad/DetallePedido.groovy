package com.curso.modelo.entidad

import groovy.transform.ToString

//@Entity
@ToString
class DetallePedido /*implements GormEntity<DetallePedido>*/{

    //id
    Double cantidad
    Double precio

    //Pedido pedido
    //Producto producto
    static belongsTo = [ pedido:Pedido,
                         producto:Producto ]

    static mapping = {
        pedido cascade:'none'
        producto cascade:'none'
    }

    static constraints = {
    }
}
