package com.curso.controlador

import com.curso.entidad.Cliente
import org.springframework.web.servlet.ModelAndView

/*
A un controlador Grails se le asocian las url que terminen en

/nombre_controlador/metodo_controlador

Si no se indica el nombre del metodo se invoca uno que se llame 'index' ó, si solo hay un método, ese

/clientes

*/

class ClientesController {

    //Por defecto los controladores de Grails son SINGLETONS
    //Si queremos que cambie debemos añadir un atributo estático a la clase llama 'scope'
    //
    //static scope = "prototype" //Se creará un controlador nuevo para procesar cada petición
    //static scope = "session"   //Se creará un controlador nuevo para procesar TODAS las peticiones relacionadas con una sesión
    //static scope = "singleton" //Se creará un único ejemplar que procesará todas las peticiones

    //Simulamos con esta lista una bb.dd.
    List<Cliente> listadoClientes = [
        new Cliente(id:1,nombre:"aaaa",direccion:"bbbb",telefono:"cccc"),
        new Cliente(2,"dddd","eeee","ffff"),
        new Cliente(3,"gggg","hhhh","iiii"),
        new Cliente(4,"jjjj","kkkk","llll"),
        new Cliente(5,"mmmm","nnnn","ññññ")
    ]

    ClientesController(){
        println "Instanciando ClientesController"
    }

    //Un controlador tendrá un método por cada pantalla
    //diferente que vaya a mostrar

    //GET /clientes/verClientes
    def verClientes() {
        println "ClientesController.verClientes()"

        //Si la vista que queremos mostrar no se llama igual que el método que la muestra
        //debemos indicarlo explícitamente

        //Utilizando Spring MVC
        //ModelAndView mav = new ModelAndView("clientes")
        //mav.addObject("listadoClientes", listadoClientes)
        //return mav

        //Utilizando el método 'render'
        render(view : "clientes",
               model: [
                       "listadoClientes":listadoClientes,
                       "clienteSel": new Cliente()
               ])
    }

    def seleccionarCliente(){
        //Cuidado que los parametros vienen todos como cadenas de texto
        //Long id = params.id

        Long id = params.long("id")

        render([
                view : "clientes",
                model : [
                        listadoClientes:listadoClientes,
                        clienteSel:listadoClientes.find({cli -> cli.id == id })
                ]
        ])
    }


    //POST /clientes/insertar
    def insertar(){
        //Params es un mapa en el que están los parámetros recibidos
        //Incluyen los query params (?) y los que vengn en el body
        println params

        //Cliente cli = new Cliente(params)
        Cliente cli = new Cliente(new Date().getTime(), params.nombre, params.direccion, params.telefono)
        listadoClientes.add(cli)

        println "ClientesController.insertar()"

        //Si hacemos render y el usuario le da a F5 se repite la
        //petición y se inserta dos veces!
        //render(view : "clientes",
        //        model: [
        //                "listadoClientes":listadoClientes
        //        ])

        //Despues de un post/put/patch haremos un redirect

        //redirect([ view: "clientes/clientes.gsp" ]) //MAL

        //En MVC no se piden vistas. Se envia una peticion a un controlador
        //que te devolverá la vista adecuada
        redirect([ controller:'clientes', action:'verClientes'])
    }


}
