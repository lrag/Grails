package com.curso.modelo.entidad

class DatosBancarios {

    String banco
    Long numeroTC

    static belongsTo = [ cliente:Cliente]

    static constraints = {
    }
}
