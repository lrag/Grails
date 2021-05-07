package com.curso.modelo.entidad

import grails.gorm.annotation.Entity
import groovy.transform.ToString
import org.grails.datastore.gorm.GormEntity

import java.time.LocalDate

//En un proyecto grails no es necesario anotar la clase con @Entity
//porque se añade con un Postprocessor
//Lo mismo sucede con la interfaz GormEntity. Solo es necesaria si el
//proyecto no es Grails
@Entity
@ToString
//GORM supone que la tabla se llama igual que la clase
//Si no lo queremos lo sobreescribirmos en la sección 'mapping'
class Cliente implements GormEntity<Cliente>{

    //GORM da por sentado que queremos que la clave se llame 'id', y que sea un Long
    //Si no es así debemos añadir la propiedad e indicarle a GORM que esa es la clave
    Long idCliente

    //Por cada propiedad de la clase GORM supondrá una columna que se llame igual
    //excepto para aquellos nombres de atributos que estén en camel case, que lo
    //'traducirá' a snake case
    //
    //Si queremos que el nombre de una columna no coincida con el de la propiedad
    String nombre
    String direccion
    String telefono
    LocalDate fechaAlta //FECHA_ALTA

    //Este extremo obpcional no es de mucha utilidad...
    //static hasMany = [ pedidos:Pedido ]

    String datoQueNoQueremosQueSePersista

    static mapping = {
        //Con esto la tabla se llamará 'clientes' en lugat de 'cliente'
        table 'clientes'

        //Sobreescribimos la configuración por defecto para los nombres de columnas
        direccion column:'dir_cliente'

        //Le idicamos cual es la clave (si no queremos que se llame 'id')
        id name:'idCliente'
    }

    static transients = ['datoQueNoQueremosQueSePersista']

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


