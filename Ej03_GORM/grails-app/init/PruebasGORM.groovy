import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.Pedido
import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.Session

import java.time.LocalDate

Map configuration = [
        'hibernate.hbm2ddl.auto':'update',
        'hibernate.show_sql':'true',
        'dataSource.url':'jdbc:h2:file:C:/h2/bbddGRAILS;USER=sa'
]
HibernateDatastore datastore = new HibernateDatastore( configuration, Cliente)
/*
//INSERT
Cliente.withNewSession({ session ->
    println "========================================"
    session.beginTransaction()

    LocalDate fecha = LocalDate.now()
    Cliente c1 = new Cliente(nombre: "Lisa", direccion: "C/Evergreen Terrace", telefono: "555", fechaAlta: fecha)
    println c1
    c1.save()
    println c1

    session.getTransaction().commit() //.rollback()
})

//SELECT POR ID
Cliente.withNewSession ({ session ->
    println "========================================"
    def c2 = Cliente.findById(1)
    println c2
})
*/

//SELECT ALL
Cliente.withNewSession ({ session ->
    println "========================================"
    def clientes = Cliente.findAll()
    clientes.forEach( {cli -> println cli })
})

/*
//CACHÉ DE PRIMER NIVEL
//SELECT POR ID
Cliente.withNewSession ({ Session session ->
    println "========================================"
    def c3a = Cliente.findById(1)
    def c3b = Cliente.findById(1)
    def c3c = Cliente.findById(1)

    c3a.nombre = "Homer"
    //c2a, c3b, c3c son referencias al mismo objeto, que está en la caché de primer nivel
    println "${c3b.nombre}, ${c3c.nombre}"
})

Cliente.withNewSession ({ Session session ->
    println "========================================"
    session.beginTransaction()
    def c4 = Cliente.findById(1)
    println c4
    c4.nombre = "Batholomew J Simpson"
    println c4
    session.getTransaction().commit()
})

//Update con todos los campos
Cliente.withNewSession ({ Session session ->
    println "========================================"
    Cliente c5 = new Cliente(id:2, nombre:"Lisa Simpson", direccion:"C/Evergreen Terrace", telefono:"123456")
    session.beginTransaction()
    //c5.save()
    c5.merge()
    session.getTransaction().commit()
})

//Update con informacion parcial
Cliente.withNewSession ({ Session session ->
    println "========================================"
    def id = 1
    def nuevoTelefono = "111222333"
    session.beginTransaction()
    Cliente c6 = Cliente.findById(id)
    c6.telefono = nuevoTelefono
    //No es necesario guardar explícitamente!
    //c5.save()
    //c5.merge()
    session.getTransaction().commit()
})
*/

Cliente.withNewSession ({ Session session ->
    println "========================================"
    session.beginTransaction()
    //Cliente c7 = Cliente.findById(1)
    //c7.delete()

    Cliente c7 = new Cliente(idCliente: 1)
    c7.attach()


    session.getTransaction().commit()
})


