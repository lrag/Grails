package com.curso.controlador

import com.curso.entidad.Cliente

//Singleton
class ClientesSesionController {

    ClientesSesionController(){
        println "Instanciando ClientesSessionController"
    }

    //GET /clientes/verClientes
    def verClientes() {


        //En una aplicación grails podemos guardar objectos en una serie de mapas implícitos
        //request[] - para acceder al mapa de los atributos del HttpServletRequest
        //session[] - para acceder al mapa de los atributos de la HttpSession
        //servletContext[] - para acceder al mapa de los atributos del ServletContext
        //
        //A parte de esos tres ámbitos clásicos en grails tenemos otro de regalo:
        //flash[]

        println "ClientesSessionController.verClientes()"
        //En los controladores de Grails disponemos de una serie de objetos implícitos
        //params: un mapa con los parámetros
        //session: un mapa con los atributos de la HttpSession
        List<Cliente> listadoClientes = session["listadoClientes"]
        if(listadoClientes==null){
            println "CREANDO LA LISTA DE CLIENTES"
            listadoClientes = []
            session["listadoClientes"] = listadoClientes
        }

        render(view : "clientes",
               model: [
                       listadoClientes:listadoClientes,
                       clienteSel: new Cliente()
               ])
    }

    def seleccionarCliente(){
        Long id = params.long("id")

        List<Cliente> listadoClientes = session["listadoClientes"]
        render([
                view : "clientes",
                model : [
                        listadoClientes:listadoClientes,
                        clienteSel:listadoClientes.find({cli -> cli.id == id })
                ]
        ])

    }

    //POST /clientes/insertar
    def insertar(){
        println "ClientesController.insertar()"
        println params
        Cliente cli = new Cliente(new Date().getTime(), params.nombre, params.direccion, params.telefono)

        List<Cliente> listadoClientes = flash["listadoClientes"]
        listadoClientes.add(cli)
        println listadoClientes

        redirect([ controller:'clientesSesion', action:'verClientes'])
    }

}
