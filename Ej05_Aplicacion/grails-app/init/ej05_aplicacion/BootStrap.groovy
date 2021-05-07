package ej05_aplicacion

class BootStrap {

    def init = { servletContext ->
        println "Ya estamos aqui"
    }
    def destroy = {
        println "Lástima que terminó"
    }
}
