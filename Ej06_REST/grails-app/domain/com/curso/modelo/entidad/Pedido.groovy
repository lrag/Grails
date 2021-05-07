package com.curso.modelo.entidad

import grails.gorm.annotation.Entity
import groovy.transform.ToString
import org.grails.datastore.gorm.GormEntity

import java.time.LocalDate

@Entity
@ToString
class Pedido implements GormEntity<Pedido>{

    //COLUMNAS

    //Long id
    String codigo
    LocalDate fecha
    String direccion

    //RELACIONES


    //Many to one
    //Se añadirá una foreing key a la tabla 'pedido' que guardará el id del cliente
    //Por defecto las relaciones contra una única entidad tienen cascade ALL
    static belongsTo = [ cliente:Cliente ]

    //One to many (cuidado)
    static hasMany = [ detalles:DetallePedido ]

    ////////////////////////////////////////////////

    static mapping = {
        /*
        NONE
        PERSIST
        MERGE
        DELETE
        DELETE_ORPHAN
        ALL
        */
        cliente cascade:'none'
        detalles cascade:'all'
    }

    static constraints = {
        id bindable:true
    }

}

