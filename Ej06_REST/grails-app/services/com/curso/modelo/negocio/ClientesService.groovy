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
        cliente.save()
        //Enviarle un correoE de bienvenida...
        println "ClientesService.insertar: ${cliente}"
    }

    void modificar(Cliente cliente){
        println "--------------------------------"
        println "ClientesService.modificar: ${cliente}"
        println cliente
        println cliente.id
        println cliente.idCliente
        println cliente.datosBancarios
        println cliente.datosBancarios.id
        //La lógica de negocio para modificar bien puede ser diferente
        //de la de insertar

        Cliente cAux = Cliente.findById(cliente.idCliente)
        cAux.setNombre(cliente.nombre)
        cAux.direccion = cliente.direccion
        cAux.telefono  = cliente.telefono
        cAux.fechaAlta = cliente.fechaAlta
        cAux.datosBancarios = cliente.datosBancarios

        cAux.save()
        println "--------------------------------"
    }

    void borrar(Integer idCliente){
        println "ClientesService.borrar: ${idCliente}"
        def cliente = Cliente.findById(idCliente)
        cliente.delete()
    }

}
