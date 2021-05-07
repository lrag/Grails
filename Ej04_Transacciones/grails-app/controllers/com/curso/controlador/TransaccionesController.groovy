package com.curso.controlador


import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService

import java.time.LocalDate

class TransaccionesController {

    //@Autowired
    ClientesService clientesService

    //GET /transacciones/clientes
    def index(){
        render( view: "clientes",
                model: [
                        listadoClientes : Cliente.findAll()
                ])
    }

    //POST /transacciones/insertarClientes
    def insertarClientes (){
        List<Cliente> clientes = [
            new Cliente(nombre: "C1", direccion: "D1", telefono: "T1", fechaAlta: LocalDate.now()),
            new Cliente(nombre: "C2", direccion: "D2", telefono: "T2", fechaAlta: LocalDate.now()),
            new Cliente(nombre: "???", direccion: "D3", telefono: "T3", fechaAlta: LocalDate.now()),
            new Cliente(nombre: "C4", direccion: "D4", telefono: "T4", fechaAlta: LocalDate.now()),
            new Cliente(nombre: "C5", direccion: "D5", telefono: "T5", fechaAlta: LocalDate.now()),
        ]

        try {
            clientesService.insertarClientes(clientes)
        } catch( e ){
            println "ERROR"
            println e.message
        }

        redirect( controller:'transacciones', action:'index' )
    }

    //POST /transacciones/borrarClientes
    def borrarClientes (){

        clientesService.borrarTodos()
        redirect( controller:'transacciones', action:'index'  )
    }
}
