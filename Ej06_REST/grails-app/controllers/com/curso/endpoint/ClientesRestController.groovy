package com.curso.endpoint

import com.curso.endpoint.dto.ClienteDTO
import com.curso.endpoint.dto.Mensaje
import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.ClientesService
import grails.converters.JSON
import grails.gorm.time.LocalDateConverter
import grails.rest.RestfulController
import org.h2.bnf.context.DbTableOrView

import java.util.stream.Collectors

class ClientesRestController {

    ClientesService clientesService

    def index() {
        def clientesDTO = Cliente
            .findAll()
            .stream()
            .map( { c -> new ClienteDTO(c) } )
            .collect(Collectors.toList())
        JSON.use("deep") {
            render contentType: "application/json", text: clientesDTO as JSON
        }
    }

    def show(Cliente cliente) {
        if(cliente == null) {
            respond(new Mensaje(codigo:404, mensaje:"El cliente no existe"), status: 404, formats: ['json'])
            return
        }
        JSON.use("deep") {
            render contentType: "application/json", text: new ClienteDTO(cliente) as JSON
        }
    }

    def save(ClienteDTO clienteDTO) {
        clientesService.insertar(clienteDTO.asCliente())
        respond(clienteDTO, formats: ['json'])
    }

    def update(ClienteDTO clienteDTO) {
        println "MODIFICAR"
        clientesService.modificar(clienteDTO.asCliente())
        respond(clienteDTO, formats: ['json'])
    }

    def delete(Integer id) {
        clientesService.borrar(id)
        respond( new Mensaje(codigo: 200, mensaje: "El cliente se ha eliminado"), formats: ['json'])
    }

}
