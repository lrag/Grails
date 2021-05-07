import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.DetallePedido
import com.curso.modelo.entidad.Pedido.Direccion
import com.curso.modelo.entidad.Pedido
import com.curso.modelo.entidad.Producto
import org.grails.orm.hibernate.HibernateDatastore

import java.time.LocalDate

Map configuration = [
        'hibernate.hbm2ddl.auto':'update',
        'hibernate.show_sql':'true',
        'dataSource.url':'jdbc:h2:file:C:/h2/bbddGRAILS;USER=sa'
]
HibernateDatastore datastore = new HibernateDatastore( configuration, Cliente, Pedido, DetallePedido, Producto)

//INSERT
//En este ejemplo insertamos un pedido al que le asociamos un cliente que tampoco existe
datastore.withNewSession({ session ->
    println "========================================"
    session.beginTransaction()

    LocalDate fecha = LocalDate.now()

    //Una entidad que no tiene valor en el id se dice que no existe (en la base de datos)
    Cliente c1 = new Cliente(nombre: "Lisa", direccion: "C/Evergreen Terrace", telefono: "555", fechaAlta: fecha)
    Direccion d1 = new Direccion(ciudad:"Chinchón", calle:"C/Falsa", numero:"123", codigoPostal: "12345")
    Pedido p1 = new Pedido(codigo: "PED-0", fecha:LocalDate.now(), direccion:d1, cliente: c1)

    //Podemos usar cascades (peligrosos si no tenemos cuidado)
    //O insertar las cosas en el orden adecuado:
    //c1.save()
    //p1.save()
    println p1
    session.getTransaction().commit() //.rollback()

})

//INSERT
//Lo lógico es que si vamos a insertar un pedido se lo asignemos a un cliente
//existente
datastore.withNewSession({ session ->
    println "========================================"
    session.beginTransaction()

    LocalDate fecha = LocalDate.now()

    //Una entidad que tiene valor en el id pero que no está en la caché de primer nivel
    //de una session está en estado 'DETACHED'
    //Cliente c1 = new Cliente(idCliente:1, nombre: "Bartholomew J Simpson", direccion: "C/Evergreen Terrace", telefono: "555", fechaAlta: fecha)
    //println "Cliente: ${c1}"

    //En realidad solo necesitamos que el cliente tenga el id correcto
    //Exige que el pedido se guarde con save(deepValidate:false)
    Cliente c1 = new Cliente(idCliente: 1)
    println "Cliente:"+c1

    //Tambien podríamos hacer
    //Cliente c1 = Cliente.findById(1)

    Direccion d1 = new Direccion(ciudad:"Chinchón", calle:"C/Falsa", numero:"123", codigoPostal: "12345")
    Pedido p1 = new Pedido(codigo: "PED-4", fecha:LocalDate.now(), direccion:d1, cliente: c1)

    //p1.save()
    println p1
    session.getTransaction().commit() //.rollback()

})

/*
datastore.withNewSession ({ session ->
    println "========================================"
    session.beginTransaction()

    //Hay mejores formas de hacer esto
    //List<Producto> productos = Producto.findAll()
    //Producto.deleteAll(productos)

    Producto p1 = new Producto(nombre:"P1",fabricante: "Fabricante 1", precio:25, existencias:1000)
    Producto p2 = new Producto(nombre:"P2",fabricante: "Fabricante 2", precio:50, existencias:1000)
    Producto p3 = new Producto(nombre:"P3",fabricante: "Fabricante 3", precio:75, existencias:1000)
    Producto p4 = new Producto(nombre:"P4",fabricante: "Fabricante 4", precio:100, existencias:1000)
    Producto p5 = new Producto(nombre:"P5",fabricante: "Gabricante 5", precio:125, existencias:1000)

    //p1.save()
    //p2.save()
    //p3.save()
    //p4.save()
    //p5.save()

    session.getTransaction().commit()

})

datastore.withNewSession ({ session ->
    println "========================================"
    session.beginTransaction()

    Cliente c1 = new Cliente(nombre:"Lisa", direccion:"C/Tal,123", telefono:"555", fechaAlta: LocalDate.now())
    Direccion d1 = new Direccion(ciudad:"Chinchón", calle:"C/Falsa", numero:"123", codigoPostal: "12345")
    Pedido p1 = new Pedido(codigo: "PED-1", fecha:LocalDate.now(), direccion:d1, cliente: c1)

    Producto prod1 = Producto.findById(1)
    DetallePedido dp1 = new DetallePedido(producto: prod1, pedido: p1, cantidad: 25, precio:25 )

    Producto prod2 = Producto.findById(2)
    DetallePedido dp2 = new DetallePedido(producto: prod2, pedido: p1, cantidad: 30, precio:50 )

    Producto prod3 = Producto.findById(3)
    DetallePedido dp3 = new DetallePedido(producto: prod3, pedido: p1, cantidad: 35, precio:75 )

    //Para que se inserte todo sin cascades ni extremos 'hasMany'
    /*
    c1.save()
    p1.save()
    //En este ejemplo los productos ya tienen id
    //prod1.save()
    //prod2.save()
    //prod3.save()
    dp1.save()
    dp2.save()
    dp3.save()
    */
/*
    //Esto es correcto
    //List<DetallePedido> detalles = [ dp1, dp2, dp3 ]
    //p1.detalles = detalles
    p1.addToDetalles(dp1)
    p1.addToDetalles(dp2)
    p1.addToDetalles(dp3)

    //No tenemos un cascade de pedido a cliente (ni lo queremos)

    c1.save()
    p1.save(deepValidate:false)

    session.getTransaction().commit()
})

datastore.withNewSession ({ session ->
    println "========================================"
    //session.beginTransaction()

    Pedido p2 = Pedido.findById(7)
    println p2.codigo+','+p2.direccion.ciudad

    session.close()

    //Lazy inicialization exception
    p2.detalles.forEach( {dp -> println dp.producto.nombre })


    //session.getTransaction().commit()
})

*/



