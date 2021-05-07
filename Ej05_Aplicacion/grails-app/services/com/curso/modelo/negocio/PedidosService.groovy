package com.curso.modelo.negocio

import com.curso.modelo.entidad.Pedido
import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class PedidosService {

    void insertarPedido(Pedido pedido){
        println "==========================================="
        //Ln...
        if(pedido.codigo == null || pedido.codigo.length()==0) {
            pedido.setCodigo("PED-"+Math.round(new Date().getTime()/10000))
        }
        pedido.save()
        println pedido
        println "==========================================="
    }

    void borrarPedido(Pedido pedido){
        pedido = Pedido.findById(pedido.id)
        pedido.delete()
    }

}
