package com.curso.modelo.negocio

import com.curso.modelo.entidad.Cliente
import grails.gorm.transactions.Transactional

import java.time.LocalDate

@Transactional
class ClientesService {

    void insertar(Cliente cliente){
        //Validar los datos...
        //Asignarle un comercial...
        //Asignarle una sucursal...
        cliente.fechaAlta = LocalDate.now()
        cliente.save()
        //Enviarle un correoE de bienvenida...
        println "ClientesService.insertar: ${cliente}"
    }

    void modificar(Cliente cliente){
        println "--------------------------------"
        println "ClientesService.modificar: ${cliente}"
        //La lógica de negocio para modificar bien puede ser diferente
        //de la de insertar

        Cliente cAux = Cliente.findById(cliente.idCliente)
        cAux.setNombre(cliente.nombre)
        cAux.direccion = cliente.direccion
        cAux.telefono = cliente.telefono

        cAux.save()
        println "--------------------------------"
    }

    void borrar(Cliente cliente){
        println "ClientesService.borrar: ${cliente}"
        cliente = Cliente.findById(cliente.idCliente)
        cliente.delete()
    }

}
