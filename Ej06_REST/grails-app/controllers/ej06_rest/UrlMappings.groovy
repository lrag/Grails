package ej06_rest

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //
        //grails url-mappings-report
        //

        //Si colocamos este 'mapeo'...
        //en 'resources' se coloca el nombre del controlador
        "/peliculas"(resources:"peliculas")
        //..es equivalente a est:
        //get "/peliculas"(controller: 'peliculas', action: 'index')
        //get "/peliculas/$id?"(controller: 'peliculas', action: 'show')
        //post "/peliculas"(controller: 'peliculas', action: 'save')
        //put "/peliculas/$id"(controller: 'peliculas', action: 'update')
        //delete "/peliculas/$id"(controller: 'peliculas', action: 'delete')

        "/clientes"(resources:"clientesRest")
        /*
        get "/clientes"(controller: 'clientesRest', action: 'index')
        get "/clientes/${id}?"(controller: 'clientesRest', action: 'show')
        post "/clientes"(controller: 'clientesRest', action: 'save')
        put "/clientes/$id"(controller: 'clientesRest', action: 'update')
        delete "/clientes/$id"(controller: 'clientesRest', action: 'delete')
        */

        //Home
        "/"(view:"/index")
        "500"(view:'/error')

    }
}

