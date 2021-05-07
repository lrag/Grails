package com.curso.endpoint.dto

import com.curso.modelo.entidad.Cliente
import groovy.transform.ToString

import java.time.LocalDate

@ToString
class ClienteDTO {

    //La feliz idea de cambiarle el id a la clase Cliente por 'idCliente'
    Integer idCliente
    String nombre
    String direccion
    String telefono
    //Días desde el 1/1/1970
    Long fechaAlta
    DatosBancariosDTO datosBancarios

    ClienteDTO(){
    }

    ClienteDTO(Cliente cliente){
        idCliente = cliente.idCliente
        nombre = cliente.nombre
        direccion = cliente.direccion
        telefono = cliente.telefono
        fechaAlta = cliente.fechaAlta.toEpochDay()
        if(cliente.datosBancarios){
            datosBancarios = new DatosBancariosDTO(cliente.datosBancarios)
        }
    }

    def asCliente(){
        LocalDate fecha = LocalDate.ofEpochDay(fechaAlta)
        return new Cliente(idCliente:idCliente, nombre:nombre, direccion:direccion, telefono: telefono, fechaAlta:fecha, datosBancarios: datosBancarios.asDatosBancarios())
    }

}



