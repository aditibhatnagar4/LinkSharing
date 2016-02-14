import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        log.info (grailsApplication.config.grails.variable)
    }
    def destroy = {
    }
}
