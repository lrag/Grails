package com.curso.controlador

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService
import org.springframework.beans.factory.annotation.Autowired

//Singleton
class ClientesController {

    //@Autowired
    ClientesService clientesService

    ClientesController(){
        println "Instanciando ClientesSessionController"
    }

    //GET /clientes/verClientes
    def verClientes() {

        println "ClientesController.verClientes()"

        render(view : "clientes",
               model: [
                       listadoClientes : Cliente.findAll(),
                       clienteSel: new Cliente()
               ])
    }

    //GET /clientes/seleccionarCliente
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
    def insertar(){
        println "ClientesController.insertar()"
        println params

        Cliente c = new Cliente(params)

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
