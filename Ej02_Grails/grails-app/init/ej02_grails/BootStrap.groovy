package ej02_grails

class BootStrap {

    def init = { servletContext -> println "Contexto inicializado"
    }

    def destroy = { println "Lástima que terminó" }

}
