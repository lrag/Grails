package com.curso.modelo.entidad

import grails.gorm.annotation.Entity
import groovy.transform.ToString
import org.grails.datastore.gorm.GormEntity

//@Entity
@ToString
class Producto /*implements GormEntity<Producto>*/ {

    //id
    String nombre
    String fabricante

    //Para simplificar
    Double precio //nos ahorramos el catálogo
    Double existencias //nos ahorramos el almancén

    //Este extremo de la relación no tiene utilidad alguna
    //static hasMany = [ detalles:DetallePedido ]

    static mapping = {
    }

    static constraints = {
        id bindable:true
    }

}
