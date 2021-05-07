package com.curso

class _01_Metodos {

    public void metodo1(){
        System.out.println("Metodo 1");
    }

    void metodo2(){
        //System.out.println("metodo1")
        println "Metodo 2"
    }

    //Si definimos el metodo con 'def' no indicamos el tipo devuelto
    def metodo3(){
        println "Metodo 3"
    }

    def "listar facturas"(){
        println "Listado las facturas..."
    }

    //Métodos que devuelven un valor
    String saludar1(){
        return "HOLA 1"
    }

    //No indicamos el tipo que devuelve
    //Se infiere
    def saludar2(){
        return "HOLA 2" //GStrig
    }

    //Metodos con parámetros.
    //No nos podemos ahorrar los tipos
    Integer sumar1(Integer s1, Integer s2){
        return s1+s2
    }

    //Metodos con parámetros.
    //No nos podemos ahorrar los tipos aqui tampoco
    def "sumar2"(Integer s1, Integer s2){
        //Si la ñultima línea de un método es una expresión se le coloca un return implícito
        /*return*/ s1+s2
    }

    //Closures

    //Si no hay parámetros nos podemos ahorrar los parentesis y la flecha
    //def closureSinParametros = () -> println "closure1"
    def closureSinParametros = { println "closure1" }

    //Con los closures podemos ahorrarnos los tipos de los parámetros (no siempre)
    def multiplicar = (m1, m2) -> m1*m2 //return implícito

}

