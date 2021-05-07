package com.curso.entidad

@groovy.transform.MapConstructor
class Cliente {

    Long id
    String nombre
    String direccion
    String telefono

    Cliente(){
        super()
    }

    Cliente(Long id, String nombre, String direccion, String telefono){
        super()
        this.id        = id
        this.nombre    = nombre
        this.direccion = direccion
        this.telefono  = telefono
    }

    String toString(){
        "$id, ${nombre}, ${direccion}, ${telefono}"
    }
}
