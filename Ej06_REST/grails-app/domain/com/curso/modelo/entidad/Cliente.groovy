package com.curso.modelo.entidad

import grails.gorm.annotation.Entity
import groovy.transform.ToString
import org.grails.datastore.gorm.GormEntity

import java.time.LocalDate

@Entity
@ToString

class Cliente implements GormEntity<Cliente>{

    Long idCliente
    String nombre
    String direccion
    String telefono
    LocalDate fechaAlta //FECHA_ALTA

    static hasOne = [ datosBancarios:DatosBancarios ]


    static mapping = {
        //Con esto la tabla se llamará 'clientes' en lugat de 'cliente'
        table 'clientes'

        //Sobreescribimos la configuración por defecto para los nombres de columnas
        direccion column:'dir_cliente'

        //Le idicamos cual es la clave (si no queremos que se llame 'id')
        id name:'idCliente'

        datosBancarios cascade:'all'
    }

    static constraints = {
        //Para que el id se tenga en cuenta en el constructor que recibe el mapa
        id bindable:true
    }

}



/*
class Factura {

    String codigo
    Double total
    Double cobrado
    //No queremos que se persista esta propiedad
    Double resto

    static transients = ['resto']
}
*/


