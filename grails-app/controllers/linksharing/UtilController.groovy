package linksharing

class UtilController {

    def index() {
        log.fatal "Testing fatal logging"
        log.warn "Testing warn logging"
        log.error "Testing error logging"
        log.info "Testing info logging"
        log.debug "Testing debug logging"
        log.trace "Testing trace logging"
    }
}
