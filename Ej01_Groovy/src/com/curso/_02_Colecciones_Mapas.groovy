package com.curso

class _02_Colecciones_Mapas {

   void listas(){

        //Java
        List<String> palabras1 = new ArrayList<>()
        palabras1.add("uno")
        palabras1.add("dos")
        palabras1.add("tres")
        println palabras1
        println palabras1.get(1)
        println palabras1[1]

        //Los corchetes significan LIST
        //Y ni no se indica lo contrario ARRAY_LIST
        def palabras2 = []
        palabras2.add("aaa")
        palabras2.add("bbb")
        palabras2.add("ccc")
        println palabras2.class
        println palabras2
        println palabras2[1] //Como si fuera un array

        //Se pueden inicializar con muy poco código
        def palabras3 = [ "123","456","789" ]
        println palabras3

        //Si no quisieramos un ArrayList:
        //'as' es una palabra reservada de Groovy
        def palabras4 = [] as LinkedList
        println palabras4.class
   }


    def sets(){
        println "---------------------------------"
        Set<String> set1 = new TreeSet<>()
        set1.add("abc")
        set1.add("def")
        set1.add("ghi")
        println set1

        def set2 = [].toSet()
        println set2.class

        //Por defecto utiliza 'HashSet'
        //Si queremos otro tipo lo indicamos con 'as'
        def set3 = [] as TreeSet<String>
        println set3.class
    }

    def maps(){
        println "---------------------------------"
        Map<String,String> mapa1 = new HashMap<>()
        def mapa2 = [] as TreeMap
        def mapa3 = [:]

        println "${mapa1} \n${mapa2} \n${mapa3}"
        println mapa1.getClass()
        println mapa2.getClass()
        println mapa3.getClass()

        //Java
        mapa1.put("clave1", "valor1")
        mapa1.put("clave2", "valor2")
        mapa1.put("clave3", "valor3")

        //Podemos utilizar la sintaxis de JS
        mapa2["clave1"] = "valor1"
        mapa2["clave2"] = "valor2"
        mapa2["clave3"] = "valor3"

        //Incluyendo la notación del punto
        mapa3.clave1 = "valor1"
        mapa3.clave2 = "valor2"
        mapa3.clave3 = "valor3"

        println mapa1
        println mapa2
        println mapa3

        //Inicializando un mapa
        def mapa4 = [ "clave1":"valor1", "clave2":"valor2", "clave3":"valor3" ]
        println mapa4

        //Podemos utilizar una variable en lugar de la clave
        def clave = "clave2"
        println mapa4[clave]

    }

}
