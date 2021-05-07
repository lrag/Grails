package com.curso.endpoint.dto

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.DatosBancarios

class DatosBancariosDTO {

    Integer id
    String banco
    Long numeroTC

    DatosBancariosDTO(){
    }

    DatosBancariosDTO(DatosBancarios datosBancarios){
        id = datosBancarios.id
        banco = datosBancarios.banco
        numeroTC = datosBancarios.numeroTC
    }

    def asDatosBancarios(){
        //Que pasa con el numeroTC?
        return new DatosBancarios(id:id, banco: banco, numeroTC: numeroTC)
    }

    String toString(){
        return "DB: ${id} ,${banco}, ${numeroTC}"
    }

}



