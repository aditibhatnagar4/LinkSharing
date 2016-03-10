package linksharing


//Class Work
class UtilController {
    def mailService
    //static defaultAction="" else by default calls index()
    def index() {
        log.fatal "Testing fatal logging"
        log.warn "Testing warn logging"
        log.error "Testing error logging"
        log.info "Testing info logging"
        log.debug "Testing debug logging"
        log.trace "Testing trace logging"
           }



    def mail() {
        mailService.sendMail {
            to "aditi.bhatnagar28@gmail.com"
            subject "Hello Fred"
            body 'How are you?'
        }

    }
}
