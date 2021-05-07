package com.curso.endpoint

import com.curso.endpoint.dto.Mensaje
import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService
import grails.converters.JSON

class ClientesRest_Con_Comentarios_Controller {

    ClientesService clientesService

    //GET    /clientes        index
    //GET    /clientes/${id}  show
    //POST   /clientes        save
    //PUT    /clientes/${id}  update
    //DELETE /clientes/${id}  delete

    def index() {
        println "ClientesRestController.index"

        //En un controlador que publique un api rest no haremos render porque implica
        //la ejecución de un gsp!

        println "============================"
        def clientes = Cliente.findAll()
        clientes.each {println it.nombre+','+it.datosBancarios.banco }

        //Cuidado que si lo dejamos así la serialización a json no será en profundidad
        //respond(Cliente.findAll(), formats: ['json'])

        //Si recibieramos query params accederíamos a ellos con el mapa implícito 'params'
        println "Parámetros recibidos"
        println params

        def json = ""
        JSON.use("deep") {
            json = Cliente.findAll() as JSON
        }

        //render ( [contentType: "application/json", text: json ] )
        render contentType: "text/json", text: json
    }

    //Grails busca un cliente cuyo id sea el parámetro de la url
    //GET /peliculas/${id}
    /*
    def show(Cliente cliente) {
        if(cliente == null) {
            respond(new Mensaje(codigo:404, mensaje:"El cliente no existe"), status: 404, formats: ['json'])
            return
        }

        JSON.use("deep") {
            render contentType: "text/json", text: cliente as JSON
        }
    }
    */

    //Tomando el id (el parametro es 'id' porque en mappings hemos puesto '$id'
    //get "/clientes/$id?/movidas/$xxx"(controller: 'clientesRest', action: 'show')
    def show(Integer id) {
        println '============================='
        println id

        Cliente cliente = Cliente.findById(id)
        if(cliente == null) {
            respond(new Mensaje(codigo:404, mensaje:"El cliente no existe"), status: 404, formats: ['json'])
            return
        }

        println "==========================="
        println cliente
        println cliente.datosBancarios.banco
        println cliente.datosBancarios.numeroTC

        //respond(cliente, formats: ['json'])

        def json
        JSON.use("deep") {
            json = cliente as JSON
        }
        render contentType: "text/json", text: json
    }

    //POST /clientes
    //Al ver que el método recibe un objeto y que en la petición recibida existe el header
    //'content-type: application/json' (o application/xml) Grails supone que queremos
    //que lea el body y cree un objeto a partir de su contenido
    def save(Cliente cliente) {
        println '============================='
        println 'Insertando'
        //println cliente

        println request.JSON
        println request.JSON.nombre

        //def clienteDTO = new ClienteDTO()
        //println clienteDTO

        clientesService.insertar(cliente)

        respond(cliente, formats: ['json'])
    }

}
