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


//        userIndex(controller: 'user', action: 'index', controllerExclude: 'console') {
//            before = {
//                if (!session.user) {
//                    redirect(controller: 'login', action: 'index')
//                    false
//                }
//            }
//        }
//        consoleCheck(controller: 'console', action: '*') {
//            before = {
//                if (session.user) {
//                    redirect(controller: 'login', action: 'index')
//                }
//            }
//        }
//
//        logincheck(controller: 'login', invert: true) {
//            before = {
//                if (!session.user) {
//                    redirect(controller: 'login', action: 'index')
//
//                }
//
//
//            }
//            after = { Map model ->
//
//
//            }
//            afterView = { Exception e ->
//
//            }
//
//
//        }
//
   }



}
