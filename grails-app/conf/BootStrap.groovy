class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        println (grailsApplication.config.grails.variable)
    }
    def destroy = {
    }
}
