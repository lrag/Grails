package com.curso.endpoint

import com.curso.endpoint.dto.Mensaje
import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService
import grails.converters.JSON

class ClientesRest_Sin_DTO_Controller {

    ClientesService clientesService

    def index() {
        JSON.use("deep") {
            render contentType: "application/json", text: Cliente.findAll() as JSON
        }
    }

    def show(Cliente cliente) {
        if(cliente == null) {
            respond(new Mensaje(codigo:404, mensaje:"El cliente no existe"), status: 404, formats: ['json'])
            return
        }

        JSON.use("deep") {
            render contentType: "application/json", text: cliente as JSON
        }
    }

    def save(Cliente cliente) {
        clientesService.insertar(cliente)
        respond(cliente, formats: ['json'])
    }

    def update(Cliente cliente) {
        clientesService.modificar(cliente)
        respond(cliente, formats: ['json'])
    }

    def delete(Cliente cliente) {
        clientesService.borrar(cliente)
        respond(cliente, formats: ['json'])
    }

}
