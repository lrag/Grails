package com.curso.modelo.entidad

class Pelicula {

    String titulo
    String director
    String genero
    String fechaEstreno

    static constraints = {
        //Para que el id se tenga cuenta en el constructor que recibe un mapa
        id bindable:true
    }

}


