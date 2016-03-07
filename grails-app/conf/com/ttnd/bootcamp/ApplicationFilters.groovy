package com.ttnd.bootcamp

import groovy.util.logging.Slf4j

@Slf4j
class ApplicationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                log.info "Going to $controllerName:$actionName:$params"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }


        }

        logincheck(controller: 'login', invert: true) {
            before = {
//                if (!session.user) {
//                    redirect(controller: 'login', action: 'index')

  //              }


            }
            after = { Map model ->


            }
            afterView = { Exception e ->

            }


        }

    }

}
