package com.curso.modelo.negocio

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.negocio.excepcion.ClienteException
import grails.gorm.transactions.Transactional
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.interceptor.TransactionAspectSupport

//Si esto fuera Spring 'a secas'
//@Service
//@Scope("singleton")

//PROPAGATION
//
//REQUIRED  <-- Por defecto
//REQUIRES_NEW
//MANDATORY
//SUPPORS
//NOT_SUPPORTED
//NEVER
//NESTED //Para aplicaciones web hechas con Portlets

@Transactional(propagation = Propagation.REQUIRED)
class ClientesService {

    @Transactional(propagation = Propagation.REQUIRED)
    void insertarCliente(Cliente cliente){
        print "Insertando el cliente ${cliente.nombre}..."

        println "OK"
        //Primero insertamos para que el efecto sea más dramático
        cliente.save()

        if( cliente.nombre.equals("???") ){
            println "MAL"
            //En Spring..
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
            //El setRollbackOnly es definitivo. No podemos arrepentirnos
            //transactionStatus.setRollbackOnly()

            //Mucho mejor controlar los errores con excepciones
            throw new ClienteException("Datos incorrectos!")
        }
    }

    @Transactional(propagation = Propagation.REQUIRED) //, noRollbackFor = ClienteException )
    void insertarClientes(List<Cliente> clientes){
        //clientes.each( { cliente -> insertarCliente(cliente) } )

        clientes.each {
            try {
                insertarCliente(it)
            } catch (ClienteException e){
                println 'Uno mal!'
            }
        }

        //Podemos preguntar por el estado de la transaccion
        println 'Rollback Only:'+transactionStatus.isRollbackOnly()
    }

    @Transactional
    void borrarTodos(){

        println "=================================="
        //Si queremos borrar más de un cliente con deleteAll necesitamos una lista con los clientes a borrar
        //List<Cliente> lista = Cliente.findAll()
        //Cliente.deleteAll(lista)

        //Podemos pedirle a los objetos de dominio que ejecuten consultas en un lenguaje muy similar a SQL
        //que se llama HQL (Hibernate Query Lenguage)
        //En este caso estamos utilizando un 'bulk delete'
        //SQL: delete from clientes
        //HQL: delete from Cliente
        //   : delete Cliente (no es obligatorio escribir 'from'
        Cliente.executeUpdate("delete Cliente")
    }

}
