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

    //RELACIONES

    //Embedded
    //Dos objetos, una tabla
    Direccion direccion

    //Many to one
    //Se añadirá una foreing key a la tabla 'pedido' que guardará el id del cliente
    //Por defecto las relaciones contra una única entidad tienen cascade ALL
    static belongsTo = [ cliente:Cliente ]

    //One to many (cuidado)
    static hasMany = [ detalles:DetallePedido ]

    ////////////////////////////////////////////////

    static embedded = ['direccion']

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
    }

    static constraints = {
    }

    //Colocamos Direccion como clase interna para que no creen una tabla con ella
    //
    static class Direccion {

        String ciudad
        String calle
        String numero
        String codigoPostal

    }

}

