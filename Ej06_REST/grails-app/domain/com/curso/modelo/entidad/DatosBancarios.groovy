package com.curso.modelo.entidad

import groovy.transform.ToString


class DatosBancarios {

    String banco
    Long numeroTC

    static belongsTo = [ cliente:Cliente]

    static constraints = {
        //No se en que cabeza cabe que por defecto se ignore el id en el constructor que recibe un mapa
        id bindable:true
    }

    static mapping = {
        cliente cascade:'none'
    }

    String toString(){
        return "DB: ${id} ,${banco}, ${numeroTC}"
    }

}



