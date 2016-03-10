class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "500"(view: '/error')
       // "404"(view:'/my404' )

        "/"(controller: 'login', action: 'index')

    }
}
