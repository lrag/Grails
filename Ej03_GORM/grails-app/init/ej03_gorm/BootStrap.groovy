package ej03_gorm

class BootStrap {

    def init = { servletContext -> println "Contexto inicializado"
    }

    def destroy = { println "Lástima que terminó" }

}
