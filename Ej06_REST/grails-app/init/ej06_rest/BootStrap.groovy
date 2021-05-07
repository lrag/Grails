package ej06_rest

class BootStrap {

    def init = { servletContext ->
        println "Ya estamos aqui"
    }
    def destroy = {
        println "Lástima que terminó"
    }
}