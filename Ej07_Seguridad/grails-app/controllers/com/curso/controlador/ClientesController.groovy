package com.curso.controlador

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService
import grails.plugin.springsecurity.annotation.Secured

import java.time.LocalDate

//Singleton
class ClientesController {

    //@Autowired
    ClientesService clientesService

    ClientesController(){
        println "Instanciando ClientesSessionController"
    }

    //GET /clientes/verClientes
    @Secured("isAuthenticated()")
    def verClientes() {

        println "ClientesController.verClientes()"

        render(view : "clientes",
               model: [
                       listadoClientes : Cliente.findAll(),
                       clienteSel: new Cliente()
               ])
    }

    //GET /clientes/seleccionarCliente
    @Secured("isAuthenticated()")
    def seleccionarCliente(){
        Long id = params.long("id")

        println "ClientesController.seleccionarCliente (${id})"

        render([
                view : "clientes",
                model : [
                        listadoClientes : Cliente.findAll(),
                        clienteSel:Cliente.findById( id )
                ]
        ])
    }

    //POST /clientes/insertar
    @Secured("hasRole('ROLE_ADMIN')")
    def insertar(){
        println "ClientesController.insertar()"
        println params

        Cliente c = new Cliente(params)

        if(c.fechaAlta == null) {
            c.fechaAlta = LocalDate.now()
        }

        if(!c.validate()){
            render([
                    view: "clientes",
                    model : [
                            listadoClientes : Cliente.findAll(),
                            clienteSel:c
                    ]
            ])
            return //<------
        }

        //ILEGAL
        //c.save()
        clientesService.insertar(c)
        redirect([controller:'clientes', action:'verClientes'])
    }

    //POST /clientes/modificar
    @Secured("hasRole('ROLE_ADMIN')")
    def modificar(){
        println "ClientesController.modificar()"
        println params

        Cliente c = new Cliente(params)
        c.setNombre(params.nombre)
        c.direccion = params.direccion
        c.telefono = params.telefono


        println "idCliente:"+c.idCliente
        println "id:"+c.id

        //ILEGAL
        //c.save()
        clientesService.modificar(c)

        redirect([controller:'clientes', action:'verClientes'])
    }

    //POST /clientes/modificar
    @Secured("hasRole('ROLE_ADMIN')")
    def borrar(){
        println "ClientesController.borrar()"
        println params

        Cliente c = new Cliente(params)

        //ILEGAL
        //c.delete()
        clientesService.borrar(c)

        redirect([controller:'clientes', action:'verClientes'])
    }



}
