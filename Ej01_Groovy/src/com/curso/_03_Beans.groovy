package com.curso

class _03_Beans {

    def prueba(){

        Cliente c1 = new Cliente()
        //Parece que estamos accediendo a un atributo publico de la clase cliente
        //pero estamos invocando 'setNombre'
        c1.nombre = "Bart"
        //Y aqui 'getNombre'
        println c1.nombre

        //Nos añaden constructores
        Cliente c2 = new Cliente([ "nombre":"Lisa", "direccion":"C/Evergreen Terrace", "telefono":"555123456" ])
        Cliente c3 = new Cliente([ "nombre":"Bender Bending Rodriguez", "direccion":"NNY" ])
        //Si el mapa incluye una clave que no corresponde con una propiedad de la clase falla
        //Cliente c4 = new Cliente([ "nombre":"Bender Bending Rodriguez", "movida":"NNY" ])
        println c2
        println c3

        //Si se recibe por parámetro un mapa se puede envíar sin los corchetes
        Cliente c5 = new Cliente( "nombre":"John McClane", "direccion":"C/NY", "telefono":"555123456" )
        println c5

        //Accediendo a las propiedades
        c5.getNombre()
        c5.nombre
        c5["nombre"]

        def propiedad = "nombre"
        println(c5[propiedad])

        println c5.getProperties()

        //EXPANDO. Como un Struct de C
        def coche = new Expando( "marca":"Fiat", "modelo":"Uno 45s", "color":"Colorao" )
        println coche

    }

}

//Solo colocan el constructor que recibe un mapa si la clase no tiene constructores explícitos
@groovy.transform.MapConstructor
class Cliente {

    //private String nombre;
    //private String direccion;
    //private String teléfono;
    //
    //gettes/setters

    //Si pueden definir atributos sin indicar el tipo
    //def nombre
    //def direccion
    //def telefono

    //Mejor indicamos el tipo...
    String nombre
    String direccion
    String telefono

    //Podemos 'sobreescribir' los getters/setters que nos generan
    void setNombre(String nombre){
        this.nombre = nombre.toUpperCase()
    }

    @Override
    String toString(){
        "${nombre}, ${direccion}, ${telefono}"
    }

}
