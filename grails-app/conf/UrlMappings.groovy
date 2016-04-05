class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "500"(view: '/my500')
        "404"(view:'/my404' )

      //  "/"(controller: 'login', action: 'index')
        "/"(controller: "user", action: "index")

    }
}
