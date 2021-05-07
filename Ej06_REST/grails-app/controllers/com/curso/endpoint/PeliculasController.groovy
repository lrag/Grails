package com.curso.endpoint

import com.curso.modelo.entidad.Pelicula
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import org.springframework.transaction.annotation.Propagation

//Si no sobreescribimos los métodos 'save', 'update' y 'delete'
//debemos configurar en el controlador las transacciones...
@Transactional(propagation = Propagation.REQUIRED)
class PeliculasController extends RestfulController<Pelicula>{

    static responseFormats = ['json', 'xml']

    PeliculasController() {
        super(Pelicula)
    }

    //GET    /peliculas        index
    //GET    /peliculas/${id}  show
    //POST   /peliculas        save
    //PUT    /peliculas/${id}  update
    //DELETE /peliculas/${id}  delete

    //Si no nos interesan las implementaciones por defecto de los métodos de RestfullController
    //los sobreescribimos
    @Override
    def save() {
        println "PeliculasController.save"
        return super.save()
    }

}
