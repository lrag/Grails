package ej04_transacciones

class BootStrap {

    def init = { servletContext -> println "Contexto inicializado"
    }

    def destroy = { println "Lástima que terminó" }

}
